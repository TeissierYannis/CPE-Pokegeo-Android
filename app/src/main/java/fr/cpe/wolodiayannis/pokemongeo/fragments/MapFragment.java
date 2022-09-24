package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
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

import java.util.Date;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.MainActivity;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.MapFragmentBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
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
        mLocationOverlay.enableFollowLocation();
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
        // if the last update is more than 5 minutes
        if (this.lastUpdate == null || (new Date().getTime() - this.lastUpdate.getTime()) > 3000) {
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
            // clear all old markers
            // Generate nb pokemon
            for (int i = 0; i < nbPokemon; i++) {
                // random pokemon
                int randomPokemon = (int) (Math.random() * totalPokemon);
                Pokemon pokemon = pokemonList.get(randomPokemon);
                // random position in a 100m radius
                double randomLat = (Math.random() * 0.0009) + 0.0001;
                double randomLon = (Math.random() * 0.0009) + 0.0001;
                // random position
                double lat = this.actualPosition.getLatitude() + randomLat;
                double lon = this.actualPosition.getLongitude() + randomLon;

                // Create marker with pokemon
                Marker marker = new Marker(map);
                marker.setPosition(new GeoPoint(lat, lon));
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                // From image ID get drawable with compat

                Drawable img = AppCompatResources.getDrawable(
                        requireContext(),
                        pokemon.getImageID()
                );
                // set drawable size
                //assert img != null;
                //img.setBounds(0, 0, 100, 100);

                try {
                    marker.setIcon(
                            img
                    );
                    map.getOverlays().add(marker);
                    // set size of icon
                    marker.setInfoWindow(null);
                    marker.setRelatedObject(pokemon);
                    marker.setOnMarkerClickListener((marker1, mapView) -> {
                        Pokemon pokemon1 = (Pokemon) marker1.getRelatedObject();
                        Logger.log("Pokemon clicked : " + pokemon1.getName());
                        return false;
                    });

                } catch (Exception e) {
                    Logger.logOnUiThreadError("[DRAWABLE] id = " + pokemon.getImageID(), e);
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
        mapController.animateTo(this.actualPosition);
        generatePokemonOnMap();
    }
}
