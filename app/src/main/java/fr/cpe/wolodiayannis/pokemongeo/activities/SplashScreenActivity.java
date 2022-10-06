package fr.cpe.wolodiayannis.pokemongeo.activities;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import org.osmdroid.config.Configuration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.BuildConfig;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.exception.CacheException;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.CaughtInventoryFetcher;
import fr.cpe.wolodiayannis.pokemongeo.listeners.ExecutorListener;
import fr.cpe.wolodiayannis.pokemongeo.threading.FetchThreading;
import fr.cpe.wolodiayannis.pokemongeo.threading.LoginThreading;
import fr.cpe.wolodiayannis.pokemongeo.threading.RegisterThreading;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    boolean animationAlreadyFetched = false;
    /**
     * Request code for permission request.
     */
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;

    private Datastore datastore;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    // Log in pop up window
    private EditText idEditText_login;
    private EditText passwordEditText_login;
    private Button loginButton_login;
    private Button signupButton_login;

    // Sign up pop up window
    private EditText emailEditText_signup;
    private EditText pseudoEditText_signup;
    private EditText passwordEditText_signup;
    private EditText passwordConfirmEditText_signup;
    private Button signupButton_signup;
    private ImageView backArrow_signup;


    private ProgressBar progressBar;
    private TextView progressBarText;

    private final int TASKS_NB = 10;
    private final int prcPerTask = 100 / TASKS_NB;

    /**
     * The executor service.
     */
    FetchThreading fetchThreading;
    LoginThreading loginThreading;
    RegisterThreading registerThreading;

    private final List<Integer> tasksDone = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.pikaColor));

        this.progressBar = findViewById(R.id.progressBar);
        this.progressBarText = findViewById(R.id.progress_bar_text);

        try {
            // Ask for permissions
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE,
                    },
                    REQUEST_PERMISSIONS_REQUEST_CODE
            );

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.LOCATION_HARDWARE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.LOCATION_HARDWARE}, 1);
            }

            // init preference manager
            Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
            Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the login dialog.
     */
    public void createLoginDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View loginPopupView = getLayoutInflater().inflate(R.layout.login_popup, null);
        dialogBuilder.setView(loginPopupView);

        idEditText_login = loginPopupView.findViewById(R.id.idEditText_login);
        passwordEditText_login = loginPopupView.findViewById(R.id.editText_password_login);
        loginButton_login = loginPopupView.findViewById(R.id.button_login_login);
        signupButton_login = loginPopupView.findViewById(R.id.signupButton_login);

        dialog = dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();

        loginButton_login.setOnClickListener(v -> {
            if (idEditText_login.getText().toString().isEmpty() || passwordEditText_login.getText().toString().isEmpty()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreenActivity.this);
                builder.setMessage("Please fill all the fields")
                        .setPositiveButton("OK", (dialog, id) -> dialog.cancel());
                AlertDialog alert = builder.create();
                alert.show();
            } else {

                // get pseudo and password
                String pseudo = idEditText_login.getText().toString();
                String password = passwordEditText_login.getText().toString();

                ExecutorListener executorListener = new ExecutorListener() {

                    @Override
                    public void onEnd(Integer taskID) {
                        // if datastore get user is not null, go to main activity
                        if (Datastore.getInstance().getUser() != null) {
                            // add toast
                            runOnUiThread(() -> {
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "Log In successful", Toast.LENGTH_SHORT).show();
                                loginThreading.shutdown();
                                animateAndInitFetching();
                            });
                        } else {
                            // Run toast in UI thread
                            runOnUiThread(() -> {
                                idEditText_login.setEnabled(true);
                                passwordEditText_login.setEnabled(true);
                                loginButton_login.setEnabled(true);
                                signupButton_login.setEnabled(true);
                                Toast.makeText(getApplicationContext(), "Incorrect pseudo or password", Toast.LENGTH_SHORT).show();
                            });
                        }
                    }

                    @Override
                    public void onLoadingTextChange(String s) {
                        changeLoadingText(s);
                    }

                    @Override
                    public void onTaskendSetProgress() {
                        setProgress();
                    }
                };

                new Thread(() -> {
                    this.loginThreading = new LoginThreading(pseudo, password);
                    this.loginThreading
                            .setupExecutor(1)
                            .setExecutorListener(executorListener)
                            .setupTasks(this)
                            .execute();
                }).start();

                // TODO : Add a spinner
                idEditText_login.setEnabled(false);
                passwordEditText_login.setEnabled(false);
                loginButton_login.setEnabled(false);
                signupButton_login.setEnabled(false);

                // close the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(passwordEditText_login.getWindowToken(), 0);
            }
        });

        signupButton_login.setOnClickListener(v -> {
            dialog.cancel();
            createSignupDialog();
        });
    }

    /**
     * Create the sign up dialog.
     */
    public void createSignupDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View signupPopupView = getLayoutInflater().inflate(R.layout.signup_popup, null);

        emailEditText_signup = signupPopupView.findViewById(R.id.emailEditText_signup);
        pseudoEditText_signup = signupPopupView.findViewById(R.id.pseudoEditText_signup);
        passwordEditText_signup = signupPopupView.findViewById(R.id.passwordEditText_signup);
        passwordConfirmEditText_signup = signupPopupView.findViewById(R.id.passwordConfirmEditText_signup);
        signupButton_signup = signupPopupView.findViewById(R.id.signupButton_signup);
        backArrow_signup = signupPopupView.findViewById(R.id.backArrow_signup);

        dialogBuilder.setView(signupPopupView);
        dialog = dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();

        backArrow_signup.setOnClickListener(v -> {
            dialog.cancel();
            createLoginDialog();
        });

        signupButton_signup.setOnClickListener(v -> {
            if (emailEditText_signup.getText().toString().isEmpty()
                    || pseudoEditText_signup.getText().toString().isEmpty()
                    || passwordEditText_signup.getText().toString().isEmpty()
                    || passwordConfirmEditText_signup.getText().toString().isEmpty()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreenActivity.this);
                builder.setMessage("Please fill all the fields")
                        .setPositiveButton("OK", (dialog, id) -> dialog.cancel());
                AlertDialog alert = builder.create();
                alert.show();

            } else if (!passwordEditText_signup.getText().toString().equals(passwordConfirmEditText_signup.getText().toString())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreenActivity.this);
                builder.setMessage("Passwords are not the same")
                        .setPositiveButton("OK", (dialog, id) -> dialog.cancel());
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                String email = emailEditText_signup.getText().toString();
                String pseudo = pseudoEditText_signup.getText().toString();
                String password = passwordEditText_signup.getText().toString();

                // New timestamp
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());

                // user is pseudo, email, 0, 0, Timestamp now, null)
                User user = new User(0, pseudo, email, 0, false, timestamp, null);

                ExecutorListener executorListener = new ExecutorListener() {

                    @Override
                    public void onEnd(Integer taskID) {
                        // if datastore get user is not null, go to main activity
                        if (Datastore.getInstance().getUser() != null) {
                            runOnUiThread(() -> {
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_SHORT).show();
                                registerThreading.shutdown();
                                animateAndInitFetching();
                            });
                        } else {
                            runOnUiThread(() -> {
                                emailEditText_signup.setEnabled(true);
                                pseudoEditText_signup.setEnabled(true);
                                passwordEditText_signup.setEnabled(true);
                                passwordConfirmEditText_signup.setEnabled(true);
                                signupButton_signup.setEnabled(true);
                                backArrow_signup.setEnabled(true);
                                Toast.makeText(getApplicationContext(), "User already exist", Toast.LENGTH_SHORT).show();
                            });
                        }
                    }

                    @Override
                    public void onLoadingTextChange(String s) {
                        changeLoadingText(s);
                    }

                    @Override
                    public void onTaskendSetProgress() {
                        setProgress();
                    }
                };

                new Thread(() -> {
                    this.registerThreading = new RegisterThreading(user, password);
                    this.registerThreading
                            .setupExecutor(1)
                            .setExecutorListener(executorListener)
                            .setupTasks(this)
                            .execute();
                }).start();
                // TODO : Add a spinner
                emailEditText_signup.setEnabled(false);
                pseudoEditText_signup.setEnabled(false);
                passwordEditText_signup.setEnabled(false);
                passwordConfirmEditText_signup.setEnabled(false);
                signupButton_signup.setEnabled(false);
                backArrow_signup.setEnabled(false);

                // close the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(passwordEditText_login.getWindowToken(), 0);
            }
        });
    }

    /**
     * On permission result.
     *
     * @param requestCode  request code
     * @param permissions  permissions
     * @param grantResults grant results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if ((grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted.
                this.datastore = Datastore.getInstance();

                // Check if user is already logged in
                // get user in cache and check if it's not null
                try {
                    User user = (User) Cache.readCache(this, "data_user");
                    if (user != null) {
                        this.datastore.setUser(user);
                        animateAndInitFetching();
                    } else {
                        createLoginDialog();
                    }
                } catch (CacheException ignored) {
                    createLoginDialog();
                }
            }
        }
    }

    private void setProgress() {
        runOnUiThread(() -> progressBar.setProgress(this.progressBar.getProgress() + this.prcPerTask));
    }

    @SuppressLint("SetTextI18n")
    private void changeLoadingText(String text) {
        runOnUiThread(() -> progressBarText.setText(text + " " + progressBar.getProgress() + "%"));
    }

    /**
     * Animation and init fetching
     */
    private void animateAndInitFetching() {
        // Use AVD for animations
        ImageView imageView = findViewById(R.id.pika_face);

        // add event listener on click on image
        imageView.setOnClickListener(v -> {
            if (!this.animationAlreadyFetched) {
                // start animation
                @SuppressLint("UseCompatLoadingForDrawables")
                AnimatedVectorDrawable avd = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_anim_pika_launcher_rounded);
                imageView.setImageDrawable(avd);
                avd.start();
                this.animationAlreadyFetched = true;
            }
        });

        // Fetch data
        if (isOnline()) {
            logOnUiThread("[ONLINE] You are online");

            progressBar.setMax(100);
            progressBar.setScaleY(2f);
            progressBar.setProgress(0);

            ExecutorListener executorListener = new ExecutorListener() {
                @Override
                public void onEnd(Integer taskID) {
                    taskEnd(taskID);
                }

                @Override
                public void onLoadingTextChange(String s) {
                    changeLoadingText(s);
                }

                @Override
                public void onTaskendSetProgress() {
                    setProgress();
                }
            };

            new Thread(() -> {
                // Create executor
                this.fetchThreading = new FetchThreading();
                this.fetchThreading
                        .setExecutorListener(executorListener)
                        .setupExecutor(9)
                        .setupTasks(this)
                        .execute();
            }).start();
        }
    }

    private void updatePokemonAndSwitchActivity() {
        List<Pokemon> pokemonList = this.fetchThreading.getPokemonList().get();
        HashMap<Integer, List<Integer>> pokemonTypes = this.fetchThreading.getPokemonTypes().get();
        HashMap<Integer, List<Integer>> pokemonAbilities = this.fetchThreading.getPokemonAbilities().get();
        HashMap<Integer, List<PokemonStat>> pokemonStats = this.fetchThreading.getPokemonStats().get();

        // set type and stats for each pokemon
        changeLoadingText("Identification of the Pokémons...");
        for (Pokemon pokemon : pokemonList) {
            pokemon.setTypes(pokemonTypes.get(pokemon.getId()));
            pokemon.setStats(pokemonStats.get(pokemon.getId()));
            pokemon.setAbilities(pokemonAbilities.get(pokemon.getId()));
            pokemon.setImageID(
                    getResources()
                            .getIdentifier(
                                    "p" + String.format("%03d", pokemon.getId()),
                                    "drawable",
                                    getPackageName()
                            )
            );
            List<Integer> typesDrawables = new ArrayList<>();
            for (int i = 0; i < pokemon.getTypes().size(); i++) {
                typesDrawables.add(
                        getResources().getIdentifier(
                                "t" + String.format("%02d", pokemon.getTypes().get(i)),
                                "drawable",
                                getPackageName()
                        ));
            }
            pokemon.setImageTypeID(typesDrawables);
            // get place of the pokemon in the list
            int pokemonIndex = pokemonList.indexOf(pokemon);
            // get the pokemon from the list
            pokemonList.set(pokemonIndex, pokemon);
        }
        setProgress();

        Datastore.getInstance().setPokemons(pokemonList)
                .setItems(this.fetchThreading.getItemsList())
                .setStats(this.fetchThreading.getStatsList())
                .setTypes(this.fetchThreading.getTypesList())
                .setAbilities(this.fetchThreading.getAbilitiesList())
                .setCaughtInventory(this.fetchThreading.getCaughtInventory().get());

        // Start MainActivity
        changeLoadingText("Pokémon are ready to fight!");
        setProgress();

        // TODO BEURKg
        CaughtInventory caughtInventoryStored = Datastore.getInstance().getCaughtInventory();
        HashMap<Pokemon, CaughtPokemon> caughtInventoryList = new HashMap<>();

        for (CaughtPokemon caughtInventory : caughtInventoryStored.getcaughtInventoryList().values()) {
            caughtInventoryList.put(Datastore.getInstance().getPokemons().get(caughtInventory.getPokemonId()), caughtInventory);
        }
        Datastore.getInstance().setCaughtInventory(new CaughtInventory(caughtInventoryList));
        try {
            (new CaughtInventoryFetcher(this)).cacheInventory(Datastore.getInstance().getCaughtInventory());
        } catch (CacheException e) {
            logOnUiThread("Error while caching inventory");
            e.printStackTrace();
        }

        Intent intent;
        if (!this.datastore.getUser().isInit()) {
            intent = new Intent(this, InitActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private void taskEnd(int taskID) {
        runOnUiThread(() -> {
            this.tasksDone.add(taskID);

            int tasksToDo = 9;
            if (this.tasksDone.size() == tasksToDo) {
                this.fetchThreading.shutdown();
                updatePokemonAndSwitchActivity();
            }
        });
    }


    /**
     * Check if the player is online.
     *
     * @return true if online, false otherwise
     */
    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo == null && !netInfo.isConnectedOrConnecting()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You need to be online to play this game. Please go online and restart the app.")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, id) -> finish());
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
        return true;
    }

}
