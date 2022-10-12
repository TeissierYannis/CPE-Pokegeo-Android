package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.MapFragmentBinding;
import fr.cpe.wolodiayannis.pokemongeo.listeners.OnPharmaciesChangeListener;
import fr.cpe.wolodiayannis.pokemongeo.listeners.OnShopsChangeListener;
import fr.cpe.wolodiayannis.pokemongeo.observers.PharmaciesObserver;
import fr.cpe.wolodiayannis.pokemongeo.observers.ShopsObserver;
import fr.cpe.wolodiayannis.pokemongeo.utils.Map;
import fr.cpe.wolodiayannis.pokemongeo.utils.Position;
import fr.cpe.wolodiayannis.pokemongeo.utils.Spawn;

/**
 * MapFragment.
 */
public class MapFragment extends Fragment {

    /**
     * Map view.
     */
    private MapView mapView;
    /**
     * Binding.
     */
    MapFragmentBinding binding;
    
    /**
     * My position.
     */
    GeoPoint actualPosition;

    Map map;
    
    OnShopsChangeListener onShopsChangeListener;
    OnPharmaciesChangeListener onPharmaciesChangeListener;

    ShopsObserver shopsObserver;
    PharmaciesObserver pharmaciesObserver;
    private Spawn spawn;

    /**
     * onCreateView.
     *
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Bind layout
        this.binding = DataBindingUtil.inflate(inflater, R.layout.map_fragment, container, false);

        this.shopsObserver = new ShopsObserver();
        this.pharmaciesObserver = new PharmaciesObserver();

        this.spawn = new Spawn(shopsObserver, pharmaciesObserver);

        // Get map
        mapView = binding.map;

        this.map = new Map(mapView, getContext());

        this.map.initializeMap()
                .addMyLocationOverlay();

        GeoPoint startPoint = this.actualPosition != null ? this.actualPosition : new GeoPoint(Datastore.getInstance().getLastLocation() != null ? Datastore.getInstance().getLastLocation() : new Location("default"));

        this.map.setMapCenter(startPoint);

        this.onShopsChangeListener = new OnShopsChangeListener() {
            @Override
            public void onShopsChange(ArrayList<POI> shops) {
                map.displayShops(shops);
            }

            @Override
            public void onShopsNeedUpdate(GeoPoint position) {
                spawn.spawnShops(position);
            }
        };

        this.onPharmaciesChangeListener = new OnPharmaciesChangeListener() {
            @Override
            public void onPharmaciesChange(ArrayList<POI> pharmacies) {
                map.displayPharmacies(pharmacies);
            }

            @Override
            public void onPharmaciesNeedUpdate(GeoPoint position) {
                spawn.spawnPharmacies(position);
            }
        };

        this.shopsObserver.setListener(onShopsChangeListener);
        this.pharmaciesObserver.setListener(onPharmaciesChangeListener);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * Set actual position.
     *
     * @param location location
     */
    public void updateLocation(Location location) {
        this.actualPosition = new GeoPoint(location);
        // Follow location
        // if the location is to far from the center
        if (!Position.isInRange((GeoPoint) this.map.getMapCenter(), this.actualPosition, 100)) {
            this.map.setMapCenter(this.actualPosition);
        } else {
            this.map.animateMapCenter(this.actualPosition);
        }

        if (this.spawn.isPokemonSpawnNeeded(this.actualPosition)) {
            this.displayPokemons();
        }

        this.spawn.isShopSpawnNeeded(this.actualPosition);
        this.spawn.isPharmacySpawnNeeded(this.actualPosition);
    }

    /**
     * Display pokemons.
     */
    public void displayPokemons() {
        this.map.displayPokemons(Datastore.getInstance().getSpawnedPokemons());
    }
}
