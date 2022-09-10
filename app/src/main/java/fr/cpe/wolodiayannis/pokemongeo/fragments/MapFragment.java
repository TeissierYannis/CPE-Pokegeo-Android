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
import androidx.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.databinding.MapFragmentBinding;


public class MapFragment extends Fragment {

    private MapView map;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MapFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.map_fragment, container, false);

        Configuration
                .getInstance()
                .load(
                        getActivity().getApplicationContext(),
                        PreferenceManager.getDefaultSharedPreferences(
                                getActivity().getApplicationContext()
                        )
                );

        map = binding.map;
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT);
        GeoPoint startPoint = new GeoPoint(48.8534, 2.3488);

        // Set the zoom
        map.getController().setZoom(20f);
        map.setMinZoomLevel(20d);
        map.setMaxZoomLevel(30d);

        IMapController mapController = map.getController();
        mapController.setCenter(startPoint);

        return binding.getRoot();
    }
}
