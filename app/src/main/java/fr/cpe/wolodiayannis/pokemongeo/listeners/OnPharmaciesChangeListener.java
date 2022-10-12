package fr.cpe.wolodiayannis.pokemongeo.listeners;

import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public interface OnPharmaciesChangeListener {
    void onPharmaciesChange(ArrayList<POI> location);

    void onPharmaciesNeedUpdate(GeoPoint position);
}
