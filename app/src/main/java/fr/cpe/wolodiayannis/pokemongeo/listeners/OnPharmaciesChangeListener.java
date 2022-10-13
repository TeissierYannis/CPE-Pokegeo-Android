package fr.cpe.wolodiayannis.pokemongeo.listeners;

import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public interface OnPharmaciesChangeListener {
    /**
     * On pharmacies change.
     * @param location location
     */
    void onPharmaciesChange(ArrayList<POI> location);

    /**
     * On pharmacies need update.
     * @param position position
     */
    void onPharmaciesNeedUpdate(GeoPoint position);
}
