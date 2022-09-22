package fr.cpe.wolodiayannis.pokemongeo;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.modules.SqlTileWriter;

import java.io.File;

import fr.cpe.wolodiayannis.pokemongeo.data.DataList;
import fr.cpe.wolodiayannis.pokemongeo.databinding.ActivityMainBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.fragments.CaughtFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.InventoryFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.MapFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.PokedexFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.PokemonDetailsFragment;
import fr.cpe.wolodiayannis.pokemongeo.listeners.BackArrowListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;

/**
 * Main activity of the app.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Actual location.
     */
    private Location currentLocation;
    /**
     * FusedLocationProviderClient.
     */
    private FusedLocationProviderClient fusedLocationClient;

    /**
     * Location handler.
     */
    private Handler locationHandler;
    /**
     * All data list.
     */
    private static DataList dataList;

    public static DataList getDataList() {
        return dataList;
    }

    /**
     * onCreate Activity.
     *
     * @param savedInstanceState Bundle
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.colorPrimaryDark));

        // Get data from intent putExtra
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dataList = (DataList) extras.getSerializable("dataList");
        }

        // init fusedLocationClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        // fetch location
        fetchLocation();
        // init location handler
        this.locationHandler = new Handler();

        // bind the activity
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        showNav(binding);

        // show base fragment
        showMap();
    }

    /**
     * Show navigation
     *
     * @param binding ActivityMainBinding
     */
    private void showNav(ActivityMainBinding binding) {
        // set color of bottom nav icons
        binding.bottomNavigation.setItemIconTintList(null);
        // init listener of the bottom bar to change fragment
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.map:
                        // if already on map, do nothing
                        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof MapFragment) {
                            return true;
                        }
                        showMap();
                        break;
                    case R.id.pokedex:
                        // if already on pokedex, do nothing
                        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof PokedexFragment) {
                            return true;
                        }
                        stopFetchingLocation();
                        showPokedex();
                        break;
                    case R.id.itemsInventory:
                        // if already on inventory, do nothing
                        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof InventoryFragment) {
                            return true;
                        }
                        stopFetchingLocation();
                        showInventory();
                        break;
                    case R.id.caught:
                        // if already on caught, do nothing
                        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof CaughtFragment) {
                            return true;
                        }
                        stopFetchingLocation();
                        showCaught();
                        break;
                }
                return true;
            }
        };
        // set listener to the bottom nav
        binding.bottomNavigation.setOnItemSelectedListener(listener);
    }

    /**
     * Show map fragment.
     */
    public void showMap() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MapFragment fragment = new MapFragment();

        startFetchingLocation();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    /**
     * Show pokedex fragment.
     */
    public void showPokedex() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokedexFragment fragment = new PokedexFragment();

        PokedexListenerInterface listener = this::showPokemonDetails;
        fragment.setPokedexListenerInterface(listener);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    /**
     * Show inventory fragment.
     */
    public void showInventory() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        InventoryFragment fragment = new InventoryFragment();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    /**
     * Show pokemon details fragment.
     *
     * @param pokemon pokemon to display
     */
    private void showPokemonDetails(Pokemon pokemon) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonDetailsFragment fragment = new PokemonDetailsFragment(pokemon);

        BackArrowListenerInterface backArrowListener = this::showPokedex;
        fragment.setBackArrowListenerInterface(backArrowListener);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    /**
     * Show caught details fragment.
     *
     * @param pokemon pokemon to display
     */
    private void showPokemonCaughtDetails(Pokemon pokemon) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonDetailsFragment fragment = new PokemonDetailsFragment(pokemon);

        BackArrowListenerInterface backArrowListener = this::showCaught;
        fragment.setBackArrowListenerInterface(backArrowListener);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    /**
     * Show caught fragment.
     */
    public void showCaught() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CaughtFragment fragment = new CaughtFragment();

        PokedexListenerInterface listener = this::showPokemonCaughtDetails;
        fragment.setPokedexListenerInterface(listener);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    /**
     * Start fetching location.
     */
    public void fetchLocation() {
        if (isLocationEnabled()) {
            // get location with task
            @SuppressLint("MissingPermission")
            Task<Location> locationTask = this.fusedLocationClient.getLastLocation();
            // add listener to task to send location to fragment
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
    }

    public boolean isLocationEnabled() {
        // check if location is enabled
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            logOnUiThreadError("[ERROR] Location not enabled");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Get current location.
     *
     * @return current location
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * On resume app, update storage and check crash log.
     */
    @Override
    protected void onResume() {
        super.onResume();
        updateStorageInfo();
    }

    /**
     * refreshes the current osmdroid cache paths with user preferences plus soe logic to work around
     * file system permissions on api23 devices. it's primarily used for out android tests.
     *
     * @param ctx the context
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
        //cache management ends her
        logOnUiThread("[DEBUG] " + Configuration.getInstance().getOsmdroidTileCache().getAbsolutePath() + "\n" +
                "Cache size: " + Formatter.formatFileSize(this, cacheSize));
    }

    /**
     * Start location handler to update location.
     */
    private void startFetchingLocation() {
        if (isLocationEnabled()) {
            this.locationHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    fetchLocation();
                    locationHandler.postDelayed(this, 100);
                }
            }, 100);
        }
    }

    /**
     * Stop location handler.
     */
    private void stopFetchingLocation() {
        this.locationHandler.removeCallbacksAndMessages(null);
    }

}