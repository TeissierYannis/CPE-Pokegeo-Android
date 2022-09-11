package fr.cpe.wolodiayannis.pokemongeo;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.format.Formatter;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.modules.SqlTileWriter;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;
import fr.cpe.wolodiayannis.pokemongeo.databinding.ActivityMainBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stats;
import fr.cpe.wolodiayannis.pokemongeo.fragments.CaughtFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.InventoryFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.MapFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.PokedexFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.PokemonDetailsFragment;
import fr.cpe.wolodiayannis.pokemongeo.listeners.BackArrowListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.utils.JsonFormatter;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationClient;

    static List<Pokemon> pokemons;
    private Handler locationHandler;

    public static List<Pokemon> getPokemonList() {
        return pokemons;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        // init OSMDroid
        // Configure OSMDroid with internet access
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();

        this.locationHandler = new Handler();

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.bottomNavigation.setItemIconTintList(null);

        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.map:
                        showMap();
                        break;
                    case R.id.pokedex:
                        stopFetchingLocation();
                        showPokedex();
                        break;
                    case R.id.inventory:
                        stopFetchingLocation();
                        showInventory();
                        break;
                    case R.id.caught:
                        stopFetchingLocation();
                        showCaught();
                        break;
                }
                return true;
            }
        };

        binding.bottomNavigation.setOnItemSelectedListener(listener);

        pokemons = fetchPokemons();

        showMap();
    }

    public void showMap() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MapFragment fragment = new MapFragment();

        startFetchingLocation();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public void showPokedex() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokedexFragment fragment = new PokedexFragment();

        PokedexListenerInterface listener = this::showPokemonDetails;
        fragment.setPokedexListenerInterface(listener);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public void showInventory() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        InventoryFragment fragment = new InventoryFragment();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void showPokemonDetails(Pokemon pokemon) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonDetailsFragment fragment = new PokemonDetailsFragment(pokemon);

        BackArrowListenerInterface backArrowListener = this::showPokedex;
        fragment.setBackArrowListenerInterface(backArrowListener);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void showPokemonCaughtDetails(Pokemon pokemon) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonDetailsFragment fragment = new PokemonDetailsFragment(pokemon);

        BackArrowListenerInterface backArrowListener = this::showCaught;
        fragment.setBackArrowListenerInterface(backArrowListener);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public void showCaught() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CaughtFragment fragment = new CaughtFragment();

        PokedexListenerInterface listener = this::showPokemonCaughtDetails;
        fragment.setPokedexListenerInterface(listener);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private List<Pokemon> fetchPokemons() {

        List<Pokemon> pokemonList = new ArrayList<>();

        InputStreamReader isr = new InputStreamReader(getResources().openRawResource(R.raw.pokemon_stats));
        JsonFormatter jsonFormatter = new JsonFormatter(isr);

        // File handling
        try {
            for (int i = 0; i < jsonFormatter.getSize(); i++) {
                JSONObject object = jsonFormatter.getResultIndex(i);

                int id = object.getInt("id");
                String name = object.getString("name");
                String species = object.getString("species");

                float height = 0;
                String sHeight = object.getString("height");
                if (!sHeight.contains("None")) {
                    height = Float.parseFloat(sHeight.substring(sHeight.indexOf('(') + 1, sHeight.indexOf('m')));
                }

                float weight = 0;
                String sWeight = object.getString("weight");
                if (!sWeight.contains("None")) {
                    weight = Float.parseFloat(sWeight.substring(sWeight.indexOf('(') + 1, sWeight.indexOf("kg")));
                }

                List<POKEMON_TYPE> types = new ArrayList<>();
                for (int j = 0; j < object.getJSONArray("type").length(); j++) {
                    types.add(POKEMON_TYPE.valueOf(object.getJSONArray("type").getString(j)));
                }

                Stats stats = new Stats(
                        object.getJSONObject("stats").getInt("hp"),
                        object.getJSONObject("stats").getInt("attack"),
                        object.getJSONObject("stats").getInt("defense"),
                        object.getJSONObject("stats").getInt("sp.atk"),
                        object.getJSONObject("stats").getInt("sp.def"),
                        object.getJSONObject("stats").getInt("speed")
                );

                String description = object.getString("description");
                int gen = object.getInt("gen");

                List<POKEMON_ABILITIES> abilities = new ArrayList<>();
                for (int j = 0; j < object.getJSONArray("abilities").length(); j++) {
                    abilities.add(POKEMON_ABILITIES.valueOf(object.getJSONArray("abilities").getString(j).replaceAll(" ", "_")));
                }
                List<Pokemon> evolutions = new ArrayList<>();

                pokemonList.add(
                        Pokemon.CREATE(
                                id,
                                name,
                                species,
                                types,
                                height,
                                weight,
                                abilities,
                                stats,
                                evolutions,
                                description,
                                gen,
                                getResources().getIdentifier(
                                        "p" + object.getString("id") + "_100",
                                        "drawable",
                                        getPackageName()
                                )
                        )
                );

            }
        } catch (JSONException e) {
            System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
        }

        return pokemonList;
    }

    public void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Task<Location> locationTask = this.fusedLocationClient.getLastLocation();
        locationTask.addOnSuccessListener(location -> {
            if (location != null) {
                currentLocation = location;
                //Toast.makeText(this, "Location: " + currentLocation.getLatitude() + ", " + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                try {
                    assert mapFragment != null;
                    mapFragment.getMapAsync(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation();
            }
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStorageInfo();
        checkForCrashLogs();
    }

    private void checkForCrashLogs() {
        //look for osmdroid crash logs
        File root = Environment.getExternalStorageDirectory();
        String pathToMyAttachedFile = "/osmdroid/crash.log";
        final File file = new File(root, pathToMyAttachedFile);
        if (!file.exists() || !file.canRead()) {
            return;
        }

        //if found, prompt user to send to
        //osmdroidbugs@gmail.com

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"osmdroidbugs@gmail.com"});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Open Map crash log");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Log data");

                        Uri uri = Uri.fromFile(file);
                        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                        startActivity(Intent.createChooser(emailIntent, "Pick an Email provider"));
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        file.delete();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Crash logs");
        builder.setMessage("Sorry, it looks like we crashed at some point, would you mind sending us the" +
                        "crash log?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    /**
     * refreshes the current osmdroid cache paths with user preferences plus soe logic to work around
     * file system permissions on api23 devices. it's primarily used for out android tests.
     *
     * @param ctx
     * @return current cache size in bytes
     */
    public static long updateStoragePreferences(Context ctx) {

        //loads the osmdroid config from the shared preferences object.
        //if this is the first time launching this app, all settings are set defaults with one exception,
        //the tile cache. the default is the largest write storage partition, which could end up being
        //this app's private storage, depending on device config and permissions

        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        //also note that our preference activity has the corresponding save method on the config object, but it can be called at any time.


        File dbFile = new File(Configuration.getInstance().getOsmdroidTileCache().getAbsolutePath() + File.separator + SqlTileWriter.DATABASE_FILENAME);
        if (dbFile.exists()) {
            return dbFile.length();
        }
        return -1;
    }

    /**
     * gets storage state and current cache size
     */
    private void updateStorageInfo() {

        long cacheSize = updateStoragePreferences(this);
        //cache management ends here
        final String state = Environment.getExternalStorageState();


        System.out.println("[DEBUG] " + Configuration.getInstance().getOsmdroidTileCache().getAbsolutePath() + "\n" +
                "Cache size: " + Formatter.formatFileSize(this, cacheSize));
    }

    private void startFetchingLocation() {
        this.locationHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchLocation();
                locationHandler.postDelayed(this, 500);
            }
        }, 500);
    }

    private void stopFetchingLocation() {
        this.locationHandler.removeCallbacksAndMessages(null);
    }
}