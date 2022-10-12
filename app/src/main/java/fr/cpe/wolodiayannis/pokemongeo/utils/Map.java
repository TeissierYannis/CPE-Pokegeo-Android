package fr.cpe.wolodiayannis.pokemongeo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.HashMap;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

public class Map {
    /**
     * Map view.
     */
    private MapView map;
    /**
     * Map controller.
     */
    private IMapController mapController;
    /**
     * Context
     */
    private Context context;

    /**
     * Constructor.
     *
     * @param map     Map view.
     * @param context Context
     */
    public Map(MapView map, Context context) {
        this.map = map;
        this.context = context;
    }

    public Map initializeMap() {
        // Clear cache
        //this.map.getTileProvider().getTileCache().clear();
        // Set tile source
        this.map.setTileSource(TileSourceFactory.MAPNIK);
        // set multi touch
        this.map.setMultiTouchControls(true);
        // set zoom buttons
        this.map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        // scale to dpi
        this.map.setTilesScaledToDpi(true);
        // set map controller
        this.mapController = map.getController();
        // Zoom
        this.mapController.setZoom(18.0);
        this.map.setMinZoomLevel(18.0);
        this.map.setMaxZoomLevel(19.0);

        return this;
    }

    public Map addMyLocationOverlay() {
        // Set location overlay
        MyLocationNewOverlay mLocationOverlay = new MyLocationNewOverlay(
                new GpsMyLocationProvider(
                        this.context
                ),
                this.map
        );
        mLocationOverlay.enableMyLocation();
        this.map.getOverlays().add(mLocationOverlay);

        return this;
    }

    public Map addPlayer() {
        // set custom marker as location marker (bitmal needed)
        //Drawable marker = AppCompatResources.getDrawable(requireContext(), R.drawable.dragon);
        //mLocationOverlay.setPersonIcon(marker);
        // Set startPoint
        return this;
    }

    public Map setMapCenter(GeoPoint position) {
        this.mapController.setCenter(position);

        return this;
    }

    public Map animateMapCenter(GeoPoint position) {
        this.mapController.animateTo(position);

        return this;
    }

    public IGeoPoint getMapCenter() {
        return this.map.getMapCenter();
    }

    /**
     * Get map view.
     *
     * @return Map view.
     */
    public MapView getMap() {
        return map;
    }

    /**
     * Get map controller.
     *
     * @return Map controller.
     */
    public IMapController getMapController() {
        return mapController;
    }

    public void displayPokemons(HashMap<Pokemon, GeoPoint> spawned) {
        // add markers
        Marker marker;

        for (Pokemon pokemon : spawned.keySet()) {
            marker = new Marker(this.map);
            marker.setPosition(spawned.get(pokemon));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            // Compat drawable
            Drawable drawable = ContextCompat.getDrawable(this.context, pokemon.getImageID());
            DrawableResizer.resize(drawable, 100, 100);
            marker.setIcon(drawable);
            marker.setTitle(pokemon.getName());
            marker.setSubDescription(pokemon.getDescription());
            this.map.getOverlays().add(marker);
        }
    }

    public void displayPharmacies(ArrayList<POI> pharmacies) {
        // add markers
        Marker marker;

        for (POI pharmacy : pharmacies) {
            marker = new Marker(this.map);
            marker.setPosition(pharmacy.mLocation);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            // Compat drawable
            Drawable drawable = ContextCompat.getDrawable(this.context, R.drawable.not_discovered);
            DrawableResizer.resize(drawable, 100, 100);
            marker.setIcon(drawable);
            marker.setTitle(pharmacy.mType);
            marker.setSubDescription(pharmacy.mDescription);

            // onclick
            marker.setOnMarkerClickListener((marker1, mapView) -> {
                // show dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
                builder.setTitle(marker1.getTitle());
                builder.setMessage(marker1.getSubDescription());
                builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
                builder.show();

                return true;
            });

            this.map.getOverlays().add(marker);
        }
    }

    public void displayShops(ArrayList<POI> shops) {
        // add markers
        Marker marker;

        for (POI shop : shops) {
            marker = new Marker(this.map);
            marker.setPosition(shop.mLocation);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            // Compat drawable
            Drawable drawable = ContextCompat.getDrawable(this.context, R.drawable.not_discovered);
            DrawableResizer.resize(drawable, 100, 100);
            marker.setIcon(drawable);
            marker.setTitle(shop.mType);
            marker.setSubDescription(shop.mDescription);

            // onclick
            marker.setOnMarkerClickListener((marker1, mapView) -> {
                // show dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
                builder.setTitle(marker1.getTitle());
                builder.setMessage(marker1.getSubDescription());
                builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
                builder.show();

                return true;
            });

            this.map.getOverlays().add(marker);
        }
    }
}
