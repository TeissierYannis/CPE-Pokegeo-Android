package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.MapFragmentBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.utils.Coordinates;
import fr.cpe.wolodiayannis.pokemongeo.utils.DrawableResizer;
import fr.cpe.wolodiayannis.pokemongeo.utils.Logger;

/**
 * MapFragment.
 */
public class MapFragment extends Fragment {

    /**
     * Map view.
     */
    private MapView map;
    /**
     * Map controller.
     */
    private IMapController mapController;
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
        map = binding.map;
        // Clear cache
        map.getTileProvider().getTileCache().clear();
        // Set tile source
        map.setTileSource(TileSourceFactory.MAPNIK);
        // set multi touch
        map.setMultiTouchControls(true);
        // set zoom buttons
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        // scale to dpi
        map.setTilesScaledToDpi(true);
        // set map controller
        mapController = map.getController();
        // Zoom
        mapController.setZoom(19.0);
        //map.setMinZoomLevel(20.0);
        //map.setMaxZoomLevel(30.0);

        // Set location overlay
        MyLocationNewOverlay mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(
                requireContext()
        ), map);
        mLocationOverlay.enableMyLocation();
        map.getOverlays().add(mLocationOverlay);
        // set custom marker as location marker (bitmal needed)
        //Drawable marker = AppCompatResources.getDrawable(requireContext(), R.drawable.dragon);
        //mLocationOverlay.setPersonIcon(marker);
        // Set startPoint
        GeoPoint startPoint = new GeoPoint(this.datastore.getLastLocation() != null ? this.datastore.getLastLocation() : new Location("default"));
        mapController.setCenter(startPoint);

        return binding.getRoot();
    }

    /**
     * Add x markers on the map for each pokemon generated
     */
    private void addPokemonMarkers(Pokemon pokemon, GeoPoint geoPoint) {
        Marker marker = new Marker(map);

        // get drawable
        Drawable drawable = AppCompatResources.getDrawable(requireContext(), pokemon.getImageID());
        // resize drawable
        Drawable resizedDrawable = DrawableResizer.resize(drawable, 80, 80);

        try {
            marker.setPosition(geoPoint);
            marker.setIcon(resizedDrawable);
            marker.setTitle(pokemon.getName());
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            // add marker to map
            map.getOverlays().add(marker);
            // set on click listener
            marker.setOnMarkerClickListener((marker1, mapView) -> {
                // show popup
                PopupWindow popup = new PopupWindow(requireContext());
                popup.setContentView(
                        LayoutInflater.from(requireContext()).inflate(R.layout.fight_popup, null)
                );

                // set full screen
                popup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

                // Display the popup at the specified location, + offsets.
                popup.showAtLocation(map, 0, 0, 0);

                return true;
            });
        } catch (Exception e) {
            Logger.logOnUiThreadError(e.getMessage());
        }
    }

    /**
     * Is date passed.
     *
     * @return boolean
     */
    private boolean isSpawnDatePassed() {
        Date now = new Date();
        Date spawnDate = this.datastore.getSpawnedPokemonExpiration();
        return now.after(spawnDate);
    }

    /**
     * Is pokemon spawned.
     *
     * @return boolean
     */
    private boolean isPokemonSpawned() {
        return this.datastore.getSpawnedPokemonExpiration() != null;
    }

    /**
     * Is player too far from last pokemon.
     *
     * @return boolean
     */
    private boolean isPlayerInRange() {
        GeoPoint playerPosition = new GeoPoint(this.datastore.getLastLocation());
        HashMap<Pokemon, GeoPoint> pokemonList = this.datastore.getSpawnedPokemons();
        for (Pokemon pokemon : pokemonList.keySet()) {
            GeoPoint pokemonPosition = pokemonList.get(pokemon);
            assert pokemonPosition != null;
            if (Coordinates.distance(playerPosition, pokemonPosition) < 100) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reload all markers.
     */
    private void reloadMarkers() {
        // Clear pokemon markers (if any)
        List<Overlay> overlays = map.getOverlays();
        for (Overlay overlay : overlays) {
            if (overlay instanceof Marker) {
                map.getOverlays().remove(overlay);
            }
        }

        // Add pokemon markers
        HashMap<Pokemon, GeoPoint> pokemonList = this.datastore.getSpawnedPokemons();
        for (Pokemon pokemon : pokemonList.keySet()) {
            GeoPoint pokemonPosition = pokemonList.get(pokemon);
            assert pokemonPosition != null;
            addPokemonMarkers(pokemon, pokemonPosition);
        }

    }

    /**
     * Generate pokemon on the map with :
     * - a random position
     * - a random pokemon
     * - a timer (5minutes)
     */
    public void generatePokemonOnMap(Location location) {
        if (!isPlayerInRange() || !isPokemonSpawned() || isSpawnDatePassed()) {
            // Generate a wave of pokemon
            HashMap<Pokemon, GeoPoint> pokemonSpawnedList = new HashMap<>();
            List<Pokemon> pokemonList = this.datastore.getPokemons();
            // Random number of pokemon
            int numberOfPokemon = (int) (Math.random() * 10) + 3;

            // Generate matrix of coordinates
            List<GeoPoint> coordinatesList = Arrays.asList(Coordinates.generateRandomPoints(new GeoPoint(location), 50, numberOfPokemon));

            // Generate pokemon list
            for (int i = 0; i < numberOfPokemon; i++) {
                Pokemon pokemon = pokemonList.get((int) (Math.random() * pokemonList.size()));
                GeoPoint geoPoint = coordinatesList.get(i);
                pokemonSpawnedList.put(pokemon, geoPoint);
                addPokemonMarkers(pokemon, geoPoint);
            }

            // Set pokemon list
            this.datastore.setSpawnedPokemons(pokemonSpawnedList);

            // Set last pokemon spawned date
            // new date + 5 minutes
            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.MINUTE, 5);
            this.datastore.setSpawnedPokemonExpiration(calendar.getTime());

            // Refresh map
            map.invalidate();
        }
        this.reloadMarkers();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
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
        if (this.actualPosition.distanceToAsDouble(map.getMapCenter()) > 100) {
            mapController.setCenter(this.actualPosition);
        } else {
            mapController.animateTo(this.actualPosition);
        }

        // Launch pokemon generation in background
        new Thread(() -> generatePokemonOnMap(location)).start();
    }
}
