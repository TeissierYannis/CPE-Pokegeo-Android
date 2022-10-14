package fr.cpe.wolodiayannis.pokemongeo.activities;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.material.navigation.NavigationBarView;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.modules.SqlTileWriter;

import java.io.File;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.ActivityMainBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.ItemInventoryFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fragments.CaughtFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.InventoryFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.MapFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.PokedexFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.PokemonDetailsFragment;
import fr.cpe.wolodiayannis.pokemongeo.listeners.BackArrowListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.observers.LocationObserver;

/**
 * Main activity of the app.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Location observer.
     */
    private LocationObserver locationObserver;

    /**
     * Location handler.
     */
    private LocationListener locationListener;

    /**
     * Location manager.
     */
    private LocationManager locationManager;
    /**
     * Datastore instance.
     */
    private Datastore datastore;

    /**
     * onCreate Activity.
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(
                    getResources().getColor(R.color.colorPrimaryDark, getTheme())
            );
        }

        this.datastore = Datastore.getInstance();
        addForDev();

        // bind the activity
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        this.locationObserver = new LocationObserver();
        // Set the location observer
        this.locationObserver.setListener(location -> {
            MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            try {
                assert mapFragment != null;
                assert location != null;
                this.datastore.setLastLocation(location);
                mapFragment.updateLocation(location);
            } catch (Exception e) {
                logOnUiThreadError("[LOCATION] " + e.getMessage());
                e.printStackTrace();
            }
        });

        showNav(binding);
        setupLocation();
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
        @SuppressLint("NonConstantResourceId")
        NavigationBarView.OnItemSelectedListener listener = item -> {
            switch (item.getItemId()) {
                case R.id.map:
                    if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof MapFragment) {
                        return true;
                    }
                    setupLocation();
                    showMap();
                    break;
                case R.id.pokedex:
                    // if already on pokedex, do nothing
                    if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof PokedexFragment) {
                        return true;
                    }
                    stopLocation();
                    showPokedex();
                    break;
                case R.id.itemsInventory:
                    // if already on inventory, do nothing
                    if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof InventoryFragment) {
                        return true;
                    }
                    stopLocation();
                    showInventory();
                    break;
                case R.id.caught:
                    // if already on caught, do nothing
                    if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof CaughtFragment) {
                        return true;
                    }
                    stopLocation();
                    showCaught();
                    break;
            }
            return true;
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
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        fragment.setPokemon(pokemon);

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
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        fragment.setPokemon(pokemon);

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
     * setup location listener.
     */
    public void setupLocation() {

        this.locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location newLocation) {
                locationObserver.set(newLocation);
                datastore.setLastLocation(newLocation);
            }

            @Override
            public void onStatusChanged(String provider, int
                    status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
                stopLocation();
            }
        };
        this.locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            logOnUiThreadError("[LOCATION] Permission not granted");
            return;
        }

        this.locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0, this.locationListener);
    }

    /**
     * Stop geo location.
     */
    public void stopLocation() {
        this.locationManager.removeUpdates(this.locationListener);
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
     * Add each item x500 times to the user's inventory and arceus.
     */
    private void addForDev() {

        // add each ball x500 to the user inventory
        for (Item item : Datastore.getInstance().getItemList().getPokeballList()) {
            Datastore.getInstance().getItemInventory().addItem(item, 500);
        }
        // add each potion x500 to the user inventory
        for (Item item : Datastore.getInstance().getItemList().getPotionList()) {
            Datastore.getInstance().getItemInventory().addItem(item, 500);
        }
        // add each berry x500 to the user inventory
        for (Item item : Datastore.getInstance().getItemList().getReviveList()) {
            Datastore.getInstance().getItemInventory().addItem(item, 500);
        }
        // add arceus to the user inventory
        Datastore.getInstance().getCaughtInventory().addPokemon(
                Datastore.getInstance().getPokemons().get(493)
        );

        new Thread(() ->
        {
            try {
                (new ItemInventoryFetcher(this)).postAndCache(Datastore.getInstance().getItemInventory());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}