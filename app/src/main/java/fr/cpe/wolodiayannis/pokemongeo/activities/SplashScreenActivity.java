package fr.cpe.wolodiayannis.pokemongeo.activities;


import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
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

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import fr.cpe.wolodiayannis.pokemongeo.BuildConfig;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.dto.UserDto;
import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.exception.CacheException;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.AbilitiesFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.ItemsFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.PokemonAbilitiesFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.PokemonStatsFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.PokemonTypesFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.PokemonsFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.StatsFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.TypesFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.UserLoginFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.UserRegisterFetcher;
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

    private final int TASKS_NB = 8;
    private final int prcPerTask = 100 / TASKS_NB;

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
                            Manifest.permission.MANAGE_EXTERNAL_STORAGE
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

                ExecutorService executor = Executors.newFixedThreadPool(8);
                List<Callable<Void>> tasks = new ArrayList<>();

                tasks.add(() -> {
                    (new UserLoginFetcher(this)).fetchAndCache(pseudo, password);
                    return null;
                });
                // invoke
                try {
                    executor.invokeAll(tasks);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // if datastore get user is not null, go to main activity

                if (this.datastore.getUser() != null) {
                    dialog.cancel();
                    // add toast
                    Toast.makeText(this, "Log In successful", Toast.LENGTH_SHORT).show();
                    animateAndInitFetching();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreenActivity.this);
                    builder.setMessage("Incorrect pseudo or password")
                            .setPositiveButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert = builder.create();
                    alert.show();
                }
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

                ExecutorService executor = Executors.newFixedThreadPool(8);
                List<Callable<Void>> tasks = new ArrayList<>();

                // New timestamp
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());

                // user is pseudo, email, 0, 0, Timestamp now, null)
                User user = new User(0, pseudo, email, 0, false, timestamp, null);

                tasks.add(() -> {
                    (new UserRegisterFetcher(this)).fetchAndCache(user, password);
                    return null;
                });
                ProgressBar spinner = findViewById(R.id.progressBar);
                // invoke
                try {
                    // add loading screen
                    spinner.setVisibility(View.VISIBLE);

                    executor.invokeAll(tasks);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                spinner.setVisibility(View.GONE);

                // if datastore get user is not null, go to main activity

                if (this.datastore.getUser() != null) {
                    dialog.cancel();
                    // add toast
                    Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show();
                    animateAndInitFetching();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreenActivity.this);
                    builder.setMessage("User is already registered")
                            .setPositiveButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert = builder.create();
                    alert.show();
                }
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
        progressBar.setProgress(this.progressBar.getProgress() + this.prcPerTask);
    }

    @SuppressLint("SetTextI18n")
    private void changeLoadingText(String text) {
        runOnUiThread(() -> progressBarText.setText(text + " " + progressBar.getProgress() + "%"));
    }

    /**
     * Animation and init fetching
     */
    public void animateAndInitFetching() {

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

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            progressBar.setMax(100);
            progressBar.setScaleY(2f);
            progressBar.setProgress(0);
            // Launch multiple tasks in parallel
            ExecutorService executor = Executors.newFixedThreadPool(8);
            List<Callable<Void>> tasks = new ArrayList<>();

            AtomicReference<List<Pokemon>> pokemonList = new AtomicReference<>(new ArrayList<>());
            AtomicReference<HashMap<Integer, List<Integer>>> pokemonAbilities = new AtomicReference<>(new HashMap<>());
            AtomicReference<HashMap<Integer, List<Integer>>> pokemonTypes = new AtomicReference<>(new HashMap<>());
            AtomicReference<HashMap<Integer, List<PokemonStat>>> pokemonStats = new AtomicReference<>(new HashMap<>());
            List<Stat> statsList = new ArrayList<>();
            List<Type> typesList = new ArrayList<>();
            List<Item> itemsList = new ArrayList<>();
            List<Ability> abilitiesList = new ArrayList<>();

            // Fetching tasks
            tasks.add(() -> {
                changeLoadingText("Fetching Pokémon...");
                pokemonList.set((new PokemonsFetcher(this)).fetchAndCache());
                setProgress();
                return null;
            });

            tasks.add(() -> {
                changeLoadingText("Pokémon's abilities training...");
                pokemonAbilities.set((new PokemonAbilitiesFetcher(this)).fetchAndCache());
                setProgress();
                return null;
            });

            tasks.add(() -> {
                changeLoadingText("Definition of Pokémon's types...");
                pokemonTypes.set((new PokemonTypesFetcher(this)).fetchAndCache());
                setProgress();
                return null;
            });

            tasks.add(() -> {
                changeLoadingText("Definition of Pokémon's stats...");
                pokemonStats.set((new PokemonStatsFetcher(this)).fetchAndCache());
                setProgress();
                return null;
            });

            tasks.add(() -> {
                changeLoadingText("Creation of statistics...");
                statsList.addAll((new StatsFetcher(this)).fetchAndCache());
                setProgress();
                return null;
            });

            tasks.add(() -> {
                changeLoadingText("Creation of types...");
                typesList.addAll((new TypesFetcher(this)).fetchAndCache());
                setProgress();
                return null;
            });

            tasks.add(() -> {
                changeLoadingText("Manufacturing of items...");
                itemsList.addAll((new ItemsFetcher(this)).fetchAndCache());
                setProgress();
                return null;
            });

            tasks.add(() -> {
                changeLoadingText("Creation of abilities...");
                abilitiesList.addAll((new AbilitiesFetcher(this)).fetchAndCache());
                setProgress();
                return null;
            });

            // Wait for all tasks to be done without blocking the UI thread
            // TODO : The UI thread is blocked for now :(
            try {
                for (Future<Void> future : executor.invokeAll(tasks)) {
                    future.get();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            // set type and stats for each pokemon
            changeLoadingText("Identification of the Pokémons...");
            for (Pokemon pokemon : pokemonList.get()) {
                System.out.println("Pokemon id : " + pokemon.getId());
                pokemon.setTypes(pokemonTypes.get().get(pokemon.getId()));
                logOnUiThread("[INFO] Add types to pokemon " + pokemon.getName());
                pokemon.setStats(pokemonStats.get().get(pokemon.getId()));
                logOnUiThread("[INFO] Add stats to pokemon " + pokemon.getName());
                pokemon.setAbilities(pokemonAbilities.get().get(pokemon.getId()));
                logOnUiThread("[INFO] Add abilities to pokemon " + pokemon.getName());
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
                int pokemonIndex = pokemonList.get().indexOf(pokemon);
                // get the pokemon from the list
                pokemonList.get().set(pokemonIndex, pokemon);
            }
            setProgress();

            this.datastore = Datastore.getInstance();
            this.datastore.setPokemons(pokemonList.get())
                    .setItems(itemsList)
                    .setStats(statsList)
                    .setTypes(typesList)
                    .setAbilities(abilitiesList);

            // Close executor
            executor.shutdown();

            // Start MainActivity
            changeLoadingText("Pokémon are ready to fight!");
            setProgress();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
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
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
        return true;
    }

}
