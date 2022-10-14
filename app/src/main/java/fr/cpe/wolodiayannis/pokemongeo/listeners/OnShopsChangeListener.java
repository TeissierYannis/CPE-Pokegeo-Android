package fr.cpe.wolodiayannis.pokemongeo.listeners;

import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public interface OnShopsChangeListener {
    /**
     * On shops change.
     *
     * @param location location
     */
    void onShopsChange(ArrayList<POI> location);

    /**
     * On shops need update.
     *
     * @param position position
     */
    void onShopsNeedUpdate(GeoPoint position);
}
