package fr.cpe.wolodiayannis.pokemongeo.listeners;

import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public interface OnShopsChangeListener {
    void onShopsChange(ArrayList<POI> location);

    void onShopsNeedUpdate(GeoPoint position);
}
