package fr.cpe.wolodiayannis.pokemongeo.activities;


import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import org.osmdroid.config.Configuration;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.BuildConfig;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.listeners.BackArrowListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.utils.InternalStorage;

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

    private boolean isLogin = false;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.pikaColor));

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

                try {
                    checkLoginAndCacheUser(); // TODO check Data from the API and cache
                    isLogin = true;
                    dialog.cancel();
                    animateAndinitFetching();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
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

                // TODO get ID from API
                int id = 1;

                int experience = 0;
                boolean isInit = false;

                // get actual timestamp
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                try {
                    postAndCacheNewUser(new User(
                            id,
                            pseudo,
                            email,
                            password,
                            experience,
                            isInit,
                            timestamp
                    ));
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                isLogin = true;
                dialog.cancel();
                animateAndinitFetching();
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
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                this.datastore = Datastore.getInstance();
                if (this.datastore.getUser() == null) {
                    createLoginDialog();
                }
            }
        }
    }

    /**
     * Animation and init fetching
     */
    public void animateAndinitFetching() {

        if (isLogin) {
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
            new FetchingAndLoading().execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class FetchingAndLoading extends AsyncTask<String, Void, String> {

        /**
         * Datastore instance.
         */
        private Datastore datastore;

        private final ProgressBar progressBar = findViewById(R.id.progressBar);
        private final TextView progressBarText = findViewById(R.id.progress_bar_text);

        private final int TASKS_NB = 9;
        private final int prcPerTask = 100 / TASKS_NB;

        public FetchingAndLoading() {
            super();
        }

        private void setProgress() {
            progressBar.setProgress(progressBar.getProgress() + prcPerTask);
        }

        @SuppressLint("SetTextI18n")
        private void changeLoadingText(String text) {
            runOnUiThread(() -> progressBarText.setText(text + " " + progressBar.getProgress() + "%"));
        }

        @Override
        @SuppressLint("DefaultLocale")
        protected String doInBackground(String... params) {

            if (isOnline()) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                logOnUiThread("[ONLINE] You are online");

                progressBar.setMax(100);
                progressBar.setScaleY(2f);
                progressBar.setProgress(0);

                try {
                    changeLoadingText("Fetching Pokémon...");
                    List<Pokemon> pokemonList = callAndCachePokemonList();
                    setProgress();

                    changeLoadingText("Creation of statistics...");
                    List<Stat> statList = callAndCacheStatList();
                    setProgress();

                    changeLoadingText("Creation of types...");
                    List<Type> typeList = callAndCacheTypeList();
                    setProgress();

                    changeLoadingText("Creation of abilities...");
                    List<Ability> abilityList = callAndCacheAbilityList();
                    setProgress();

                    changeLoadingText("Manufacturing of items...");
                    List<Item> itemList = callAndCacheItemList();
                    setProgress();

                    changeLoadingText("Pokémon's abilities training...");
                    HashMap<Integer, List<Integer>> abilityListForEachPokemon = callAndCachePokemonAbilitiesList();
                    setProgress();

                    changeLoadingText("Definition of Pokémon's types...");
                    HashMap<Integer, List<Integer>> typeListForEachPokemon = callAndCachePokemonTypesList();
                    setProgress();

                    changeLoadingText("Definition of Pokémon's stats...");
                    HashMap<Integer, List<PokemonStat>> statsListForEachPokemon = callAndCachePokemonStatList();
                    setProgress();

                    changeLoadingText("Identification of the Pokémons...");
                    for (Pokemon pokemon : pokemonList) {
                        System.out.println("Pokemon id : " + pokemon.getId());
                        pokemon.setTypes(typeListForEachPokemon.get(pokemon.getId()));
                        logOnUiThread("[INFO] Add types to pokemon " + pokemon.getName());
                        pokemon.setStats(statsListForEachPokemon.get(pokemon.getId()));
                        logOnUiThread("[INFO] Add stats to pokemon " + pokemon.getName());
                        pokemon.setAbilities(abilityListForEachPokemon.get(pokemon.getId()));
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
                        int pokemonIndex = pokemonList.indexOf(pokemon);
                        // get the pokemon from the list
                        pokemonList.set(pokemonIndex, pokemon);
                    }
                    setProgress();

                    this.datastore = Datastore.getInstance();
                    this.datastore.setPokemons(pokemonList)
                            .setItems(itemList)
                            .setStats(statList)
                            .setTypes(typeList)
                            .setAbilities(abilityList);

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return "Executed";
        }

        /**
         * change to main after fetching
         *
         * @param result result of the fetching
         */
        @Override
        protected void onPostExecute(String result) {

            Intent intent;
            if(!datastore.getUser().getIsInit()) {
                intent = new Intent(getApplicationContext(), InitActivity.class);
            } else {
                intent = new Intent(getApplicationContext(), MainActivity.class);
            }
            startActivity(intent);
            // close this activity
            finish();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
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

    private List<Ability> callAndCacheAbilityList() throws IOException, ClassNotFoundException {
        List<Ability> abilityList = new ArrayList<>();
        try {
            abilityList = (List<Ability>) InternalStorage.readObject(this, "data_abilities");
            logOnUiThread("[CACHE] Ability list loaded from cache");
        } catch (Exception e) {
            try {
                abilityList = DataFetcher.fetchAbilityList().getAbilityList();
                InternalStorage.writeObject(this, "data_abilities", abilityList);
                logOnUiThread("[CACHE] Ability list cached");
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Abilities list cannot be cached : " + exception.getMessage());
            }
        }
        return abilityList;
    }

    private List<Item> callAndCacheItemList() throws IOException, ClassNotFoundException {
        List<Item> itemList = new ArrayList<>();
        try {
            itemList = (List<Item>) InternalStorage.readObject(this, "data_items");
            logOnUiThread("[CACHE] Item list loaded from cache");
        } catch (Exception e) {
            try {
                itemList = DataFetcher.fetchItemList().getItemList();
                InternalStorage.writeObject(this, "data_items", itemList);
                logOnUiThread("[CACHE] Item list cached");
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Items list cannot be cached : " + exception.getMessage());
            }
        }
        return itemList;
    }

    private List<Pokemon> callAndCachePokemonList() throws IOException, ClassNotFoundException {
        List<Pokemon> pokemonList = new ArrayList<>();
        try {
            pokemonList = (List<Pokemon>) InternalStorage.readObject(this, "data_pokemons");
            logOnUiThread("[CACHE] Pokemon list loaded from cache");
        } catch (Exception e) {
            try {
                pokemonList = DataFetcher.fetchPokemonList().getPokemonList();
                InternalStorage.writeObject(this, "data_pokemons", pokemonList);
                logOnUiThread("[CACHE] Pokemon list cached");
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Pokemon list cannot be cached : " + exception.getMessage());
                exception.printStackTrace();
            }
        }

        return pokemonList;
    }

    private HashMap<Integer, List<Integer>> callAndCachePokemonAbilitiesList() {
        HashMap<Integer, List<Integer>> abilityList = new HashMap<>();
        try {
            abilityList = (HashMap<Integer, List<Integer>>) InternalStorage.readObject(this, "data_pokemon_abilities");
        } catch (Exception e) {
            try {
                abilityList = DataFetcher.fetchPokemonAbilities();
                InternalStorage.writeObject(this, "data_pokemon_abilities", abilityList);
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Pokemon abilities list cannot be cached : " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        return abilityList;
    }

    private HashMap<Integer, List<PokemonStat>> callAndCachePokemonStatList() {
        HashMap<Integer, List<PokemonStat>> statList = new HashMap<>();
        try {
            statList = (HashMap<Integer, List<PokemonStat>>) InternalStorage.readObject(this, "data_pokemon_stats");
        } catch (Exception e) {
            try {
                statList = DataFetcher.fetchPokemonStats();
                InternalStorage.writeObject(this, "data_pokemon_stats", statList);
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Pokemon stats list cannot be cached : " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        return statList;
    }

    private HashMap<Integer, List<Integer>> callAndCachePokemonTypesList() {
        HashMap<Integer, List<Integer>> typesList = new HashMap<>();
        try {
            typesList = (HashMap<Integer, List<Integer>>) InternalStorage.readObject(this, "data_pokemon_types");
        } catch (Exception e) {
            try {
                typesList = DataFetcher.fetchPokemonTypes();
                InternalStorage.writeObject(this, "data_pokemon_types", typesList);
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Pokemon types list cannot be cached : " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        return typesList;
    }

    private List<Stat> callAndCacheStatList() throws IOException, ClassNotFoundException {
        List<Stat> statList = new ArrayList<>();
        try {
            statList = (List<Stat>) InternalStorage.readObject(this, "data_stats");
            logOnUiThread("[CACHE] Stat list loaded from cache");
        } catch (Exception e) {
            try {
                statList = DataFetcher.fetchStatList().getStatsList();
                InternalStorage.writeObject(this, "data_stats", statList);
                logOnUiThread("[CACHE] Stat list cached");
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Stat list cannot be cached : " + exception.getMessage());
            }
        }
        return statList;
    }

    private List<Type> callAndCacheTypeList() throws IOException, ClassNotFoundException {
        List<Type> typeList = new ArrayList<>();
        try {
            typeList = (List<Type>) InternalStorage.readObject(this, "data_types");
            logOnUiThread("[CACHE] Type list loaded from cache");
        } catch (Exception e) {
            try {
                typeList = DataFetcher.fetchTypeList().getTypeList();
                InternalStorage.writeObject(this, "data_types", typeList);
                logOnUiThread("[CACHE] Type list cached");
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Types list cannot be cached : " + exception.getMessage());
            }
        }
        return typeList;
    }

    private void postAndCacheNewUser(User user) throws IOException, ClassNotFoundException {
        try {
            // DataFetcher.createUser(user); // TODO POST NEW USER
            InternalStorage.writeObject(this, "data_user", user);
            logOnUiThread("[CACHE] User cached");
            this.datastore.setUser(user);
        } catch (Exception e) {
            logOnUiThreadError("[CACHE] User cannot be cached : " + e.getMessage());
        }
    }

    private void checkLoginAndCacheUser() throws IOException, ClassNotFoundException {

        User user = new User(
                1,
                "test",
                "test",
                "test",
                0,
                true,
                new Timestamp(System.currentTimeMillis())
        );

        try {
            //DataFetcher.checkUser(user.getEmail(), user.getPassword()); // TODO CHECK LOGIN
            InternalStorage.writeObject(this, "data_user", user);
            logOnUiThread("[CACHE] User cached");
            this.datastore.setUser(user);
        } catch (Exception e) {
            logOnUiThreadError("[CACHE] User cannot be cached : " + e.getMessage());
        }
    }
}
