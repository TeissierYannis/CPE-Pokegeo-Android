package fr.cpe.wolodiayannis.pokemongeo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.activities.MainActivity;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.fragments.FightFragment;

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

    /**
     * Init map.
     * @return Map.
     */
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
        this.mapController.setZoom(16.0);
        this.map.setMinZoomLevel(18.0);
        this.map.setMaxZoomLevel(19.0);

        return this;
    }

    /**
     * Add location overlay.
     * @return Map.
     */
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

    /**
     * Add custom player.
     * @return Map.
     */
    public Map addPlayer() {
        // set custom marker as location marker (bitmal needed)
        //Drawable marker = AppCompatResources.getDrawable(requireContext(), R.drawable.dragon);
        //mLocationOverlay.setPersonIcon(marker);
        // Set startPoint
        return this;
    }

    /**
     * Set map center.
     * @param position Position.
     * @return Map.
     */
    public Map setMapCenter(GeoPoint position) {
        this.mapController.setCenter(position);

        return this;
    }

    /**
     * Animate to center.
     * @param position Position.
     * @return Map.
     */
    public Map animateMapCenter(GeoPoint position) {
        this.mapController.animateTo(position);

        return this;
    }

    /**
     * get map center.
     * @return GeoPoint.
     */
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

    /**
     * Display pokemons marker
     * @param spawned Pokemons.
     */
    public void displayPokemons(HashMap<Pokemon, GeoPoint> spawned) {
        // add markers
        Marker marker;

        // remove all previous markers
        List<Overlay> overlays = this.map.getOverlays();
        for (Overlay overlay : overlays) {
            if (overlay instanceof Marker) {
                overlays.remove(overlay);
            }
        }

        for (Pokemon pokemon : spawned.keySet()) {
            marker = new Marker(this.map);
            marker.setPosition(spawned.get(pokemon));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            // get drawable

            Drawable drawable = AppCompatResources.getDrawable(this.context, pokemon.getImageID());
            Drawable resized = DrawableResizer.resize(drawable, 70, 70);
            marker.setIcon(resized);
            marker.setTitle(pokemon.getName());
            marker.setSubDescription(pokemon.getDescription());
            this.map.getOverlays().add(marker);
        }
    }

    /**
     * Display pharmacy marker
     * @param pharmacies Pharmacies.
     */
    public void displayPharmacies(ArrayList<POI> pharmacies) {
        // add markers
        Marker marker;

        // first clear all marker named pharmacy
        List<Overlay> overlays = this.map.getOverlays();
        for (Overlay overlay : overlays) {
            if (overlay instanceof Marker) {
                Marker marker1 = (Marker) overlay;
                if (marker1.getTitle().equals("pharmacy")) {
                    overlays.remove(overlay);
                }
            }
        }

        for (POI pharmacy : pharmacies) {
            marker = new Marker(this.map);
            marker.setPosition(pharmacy.mLocation);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            // Get image ID
            int imageID = this.context.getResources().getIdentifier("not_discovered", "drawable", this.context.getPackageName());
            Drawable drawable = AppCompatResources.getDrawable(this.context, imageID);
            Drawable resized = DrawableResizer.resize(drawable, 70, 70);
            marker.setIcon(resized);
            marker.setTitle("pharmacy");
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

    /**
     * display shops marker
     * @param shops Shops.
     */
    public void displayShops(ArrayList<POI> shops) {
        // add markers
        Marker marker;

        // first clear all marker named shop
        List<Overlay> overlays = this.map.getOverlays();
        for (Overlay overlay : overlays) {
            if (overlay instanceof Marker) {
                Marker marker1 = (Marker) overlay;
                if (marker1.getTitle().equals("shop")) {
                    overlays.remove(overlay);
                }
            }
        }

        for (POI shop : shops) {
            marker = new Marker(this.map);
            marker.setPosition(shop.mLocation);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            // Compat drawable
            VectorDrawable drawable = (VectorDrawable) ContextCompat.getDrawable(this.context, R.drawable.shop);
            assert drawable != null;
            Drawable resizedDrawable = DrawableResizer.resizeVectorDrawable(drawable, 100, 100);
            marker.setIcon(resizedDrawable);
            marker.setTitle("shop");
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
