package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import fr.cpe.wolodiayannis.pokemongeo.MainActivity;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.databinding.MapFragmentBinding;


public class MapFragment extends Fragment {

    private MapView map;
    MapFragmentBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        this.binding = DataBindingUtil.inflate(inflater, R.layout.map_fragment, container, false);

        initMap(binding);

        return binding.getRoot();
    }

    public void getMapAsync(MainActivity mainActivity) {
        System.out.println("getMapAsync called : " + mainActivity.getCurrentLocation());
        initMap(binding);
        // Location to geopoint
        GeoPoint startPoint = new GeoPoint(mainActivity.getCurrentLocation().getLatitude(), mainActivity.getCurrentLocation().getLongitude());
        // Center map on current location
        map.getController().setCenter(startPoint);
        // Zoom on current location

    }

    // Init map with current location
    public void initMap(MapFragmentBinding binding) {
        this.map = binding.map;
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        map.setTilesScaledToDpi(true);

        IMapController mapController = map.getController();
        // Zoom
        mapController.setZoom(20.0);
        map.setMinZoomLevel(20.0);
        map.setMaxZoomLevel(30.0);

        //MyLocationNewOverlay mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(
        //        requireContext()
        //), map);
        //mLocationOverlay.enableMyLocation();
        //map.getOverlays().add(mLocationOverlay);
    }
}
