package fr.cpe.wolodiayannis.pokemongeo;

import static java.lang.Thread.sleep;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
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

        progressBar = findViewById(R.id.progressBar);
        progressBarText = findViewById(R.id.progress_bar_text);

        progressBar.setMax(100);
        progressBar.setScaleY(2f);

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
                new FetchingAndLoading(progressBar).execute();
            }
        }
    }

    private class FetchingAndLoading extends AsyncTask<String, Void, String> {

        private final ProgressBar progressBar;

        public FetchingAndLoading(ProgressBar progressBar) {
            super();
            this.progressBar = progressBar;
        }

        @Override
        protected String doInBackground(String... params) {

            if (isOnline()) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                System.out.println("[ONLINE] You are online");

                try {
                    progressBar.setProgress(0);
                    List<Pokemon> pokemonList = callAndCachePokemonList();
                    progressBar.setProgress(20);
                    List<Item> itemList = callAndCacheItemList();
                    progressBar.setProgress(40);
                    List<Stat> statList = callAndCacheStatList();
                    progressBar.setProgress(60);
                    List<Type> typeList = callAndCacheTypeList();
                    progressBar.setProgress(80);
                    List<Ability> abilityList = callAndCacheAbilityList();
                    progressBar.setProgress(100);
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
            System.out.println("[CACHE] Ability list loaded from cache");
        } catch (Exception e) {
            try {
                abilityList = DataFetcher.fetchAbilityList().getAbilityList();
                InternalStorage.writeObject(this, "data_abilities", abilityList);
                System.out.println("[CACHE] Ability list cached");
            } catch (Exception exception) {
                System.err.println("[CACHE] Abilities list cannot be cached : " + exception.getMessage());
            }
        }
        return abilityList;
    }

    private List<Item> callAndCacheItemList() throws IOException, ClassNotFoundException {
        List<Item> itemList = new ArrayList<>();
        try {
            itemList = (List<Item>) InternalStorage.readObject(this, "data_items");
            System.out.println("[CACHE] Item list loaded from cache");
        } catch (Exception e) {
            try {
                itemList = DataFetcher.fetchItemList().getItemList();
                InternalStorage.writeObject(this, "data_items", itemList);
                System.out.println("[CACHE] Item list cached");
            } catch (Exception exception) {
                System.err.println("[CACHE] Items list cannot be cached : " + exception.getMessage());
            }
        }
        return itemList;
    }

    private List<Pokemon> callAndCachePokemonList() throws IOException, ClassNotFoundException {
        List<Pokemon> pokemonList = new ArrayList<>();
        try {
            pokemonList = (List<Pokemon>) InternalStorage.readObject(this, "data_pokemons");
            System.out.println("[CACHE] Pokemon list loaded from cache");
        } catch (Exception e) {
            try {
                pokemonList = DataFetcher.fetchPokemonList().getPokemonList();
                InternalStorage.writeObject(this, "data_pokemons", pokemonList);
                System.out.println("[CACHE] Pokemon list cached");
            } catch (Exception exception) {
                System.err.println("[CACHE] Pokemon list cannot be cached : " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        return pokemonList;
    }

    private List<Stat> callAndCacheStatList() throws IOException, ClassNotFoundException {
        List<Stat> statList = new ArrayList<>();
        try {
            statList = (List<Stat>) InternalStorage.readObject(this, "data_stats");
            System.out.println("[CACHE] Stat list loaded from cache");
        } catch (Exception e) {
            try {
                statList = DataFetcher.fetchStatList().getStatsList();
                InternalStorage.writeObject(this, "data_stats", statList);
                System.out.println("[CACHE] Stat list cached");
            } catch (Exception exception) {
                System.err.println("[CACHE] Stat list cannot be cached : " + exception.getMessage());
            }
        }
        return statList;
    }

    private List<Type> callAndCacheTypeList() throws IOException, ClassNotFoundException {
        List<Type> typeList = new ArrayList<>();
        try {
            typeList = (List<Type>) InternalStorage.readObject(this, "data_types");
            System.out.println("[CACHE] Type list loaded from cache");
        } catch (Exception e) {
            try {
                typeList = DataFetcher.fetchTypeList().getTypeList();
                InternalStorage.writeObject(this, "data_types", typeList);
                System.out.println("[CACHE] Type list cached");
            } catch (Exception exception) {
                System.err.println("[CACHE] Types list cannot be cached : " + exception.getMessage());
            }
        }
        return typeList;
    }
}
