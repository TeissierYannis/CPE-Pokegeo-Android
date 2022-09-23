package fr.cpe.wolodiayannis.pokemongeo;


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
import android.view.Window;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.data.DataList;
import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.utils.InternalStorage;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    /**
     * All data list.
     */
    private static DataList dataList;

    boolean animationAlreadyFetch = false;

    private ProgressBar progressBar;

    private TextView progressBarText;

    /**
     * Request code for permission request.
     */
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;


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
     * On permission result, fetch location.
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
                // Use AVD for animations
                ImageView imageView = findViewById(R.id.pika_face);

                // add event listener on click on image
                imageView.setOnClickListener(v -> {
                    if (!this.animationAlreadyFetch) {
                        // start animation
                        AnimatedVectorDrawable avd = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_anim_pika_launcher_rounded);
                        imageView.setImageDrawable(avd);
                        avd.start();
                        this.animationAlreadyFetch = true;
                    }
                });

                new FetchingAndLoading().execute();
            }
        }
    }

    private class FetchingAndLoading extends AsyncTask<String, Void, String> {

        private final ProgressBar progressBar = findViewById(R.id.progressBar);
        private final TextView progressBarText = findViewById(R.id.progress_bar_text);


        private final int TASKS_NB = 5;
        private final int prcPerTask = 100 / TASKS_NB;

        public FetchingAndLoading() {
            super();
        }

        private void setProgress() {
            progressBar.setProgress(progressBar.getProgress() + prcPerTask);
        }

        @Override
        protected String doInBackground(String... params) {

            if (isOnline()) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                logOnUiThread("[ONLINE] You are online");

                progressBar.setMax(100);
                progressBar.setScaleY(2f);
                progressBar.setProgress(0);

                try {
                    List<Pokemon> pokemonList = callAndCachePokemonList();
                    HashMap<Integer, List<Integer>> abilityListForEachPokemon = callAndCachePokemonAbilitiesList();
                    HashMap<Integer, List<Integer>> typeListForEachPokemon = callAndCachePokemonTypesList();
                    for (Pokemon pokemon : pokemonList) {
                        pokemon.setAbilities(abilityListForEachPokemon.get(pokemon.getId()));
                        System.out.println("Pokemon id : " + pokemon.getId());
                        pokemon.setTypes(typeListForEachPokemon.get(pokemon.getId()));
                        // get place of the pokemon in the list
                        int pokemonIndex = pokemonList.indexOf(pokemon);
                        // get the pokemon from the list
                        pokemonList.set(pokemonIndex, pokemon);
                        logOnUiThread("[INFO] Add abilities to pokemon " + pokemon.getName());
                    }
                    setProgress();
                    List<Item> itemList = callAndCacheItemList();
                    setProgress();
                    List<Stat> statList = callAndCacheStatList();
                    setProgress();
                    List<Type> typeList = callAndCacheTypeList();
                    setProgress();
                    List<Ability> abilityList = callAndCacheAbilityList();
                    setProgress();
                    dataList = new DataList(pokemonList, itemList, statList, typeList, abilityList);

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
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            // pass data to main activity
            intent.putExtra("dataList", dataList);
            startActivity(intent);
            // close this activity
            finish();
        }

        @Override
        protected void onPreExecute() {
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
}
