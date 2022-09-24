package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    GeoPoint actualPosition;

    Date lastUpdate;

    /**
     * onCreateView.
     *
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState savedInstanceState
     * @return view
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
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
     * Generate pokemon on the map with :
     * - a random position
     * - a random pokemon
     * - a timer (5minutes)
     */
    public void generatePokemonOnMap() {
        // LOG CALl
        // if the last update is more than 5 minutes
        if (this.lastUpdate == null || (new Date().getTime() - this.lastUpdate.getTime()) > 3000 && this.datastore.getLastLocation() != null) {
            Logger.log("generatePokemonOnMap called : Location : " + this.datastore.getLastLocation());

            this.lastUpdate = new Date();

            // get last five overlay and remove them
            for (int i = 0; i < 5; i++) {
                if (map.getOverlays().size() > 1) {
                    map.getOverlays().remove(map.getOverlays().size() - 1);
                }
            }

            // random between 3 and 10
            int nbPokemon = (int) (Math.random() * 7) + 3;
            // list of nb pokemon
            List<Pokemon> pokemonList = this.datastore.getPokemons();
            int totalPokemon = pokemonList.size();

            // Get generateRandomPointsWithCovariance
            // TODO : Don't work
            List<GeoPoint> geoPoints = Arrays.asList(Coordinates.generateRandomPointsWithCovariance(new GeoPoint(this.datastore.getLastLocation()), 0.0001, nbPokemon));

            // for each geoPoint generate a pokemon
            for (int i = 0; i < nbPokemon; i++) {
                // get random pokemon
                Pokemon pokemon = pokemonList.get((int) (Math.random() * totalPokemon));

                // get drawable
                Drawable drawable = AppCompatResources.getDrawable(requireContext(), pokemon.getImageID());
                // resize drawable
                Drawable resizedDrawable = DrawableResizer.resize(drawable, 100, 100);
                // set marker
                Marker marker = new Marker(map);
                try {

                    marker.setPosition(geoPoints.get(i));
                    marker.setIcon(resizedDrawable);
                    marker.setTitle(pokemon.getName());
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    // add marker to map
                    map.getOverlays().add(marker);

                    // set on click listener
                    marker.setOnMarkerClickListener((marker1, mapView) -> {
                        Logger.log("Marker clicked");
                        return false;
                    });
                } catch (Exception e) {
                    Logger.log(e.getMessage());
                }

            }
        }
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
        generatePokemonOnMap();
    }
}
