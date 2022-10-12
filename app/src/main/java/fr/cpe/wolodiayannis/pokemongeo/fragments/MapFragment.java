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

import org.osmdroid.bonuspack.location.NominatimPOIProvider;
import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.MapFragmentBinding;
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
     * Datastore.
     */
    Datastore datastore;

    /**
     * My position.
     */
    GeoPoint actualPosition;

    Map map;

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

        this.datastore = Datastore.getInstance();

        // Get map
        mapView = binding.map;

        this.map = new Map(mapView, getContext());

        this.map.initializeMap()
                .addMyLocationOverlay();

        GeoPoint startPoint = new GeoPoint(this.datastore.getLastLocation() != null ? this.datastore.getLastLocation() : new Location("default"));

        this.map.setMapCenter(startPoint);

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
    }

    /**
     * Spawn pokemons.
     */
    public void spawnPokemons() {
        Spawn spawn = new Spawn();
        spawn.spawnPokemons();
    }

    /**
     * Display pokemons.
     */
    public void displayPokemons() {
        this.map.displayPokemons(Datastore.getInstance().getSpawnedPokemons());
    }

    public void spawnPharmacies() {
        Spawn spawn = new Spawn();
        spawn.spawnPharmacies((GeoPoint) this.map.getMapCenter());
    }

    public void displayPharmacies() {
        this.map.displayPharmacies(Datastore.getInstance().getPharmacies());
    }

    public void spawnShops() {
        Spawn spawn = new Spawn();
        spawn.spawnShops((GeoPoint) this.map.getMapCenter());
    }

    public void displayShops() {
        this.map.displayShops(Datastore.getInstance().getShops());
    }
}
