package fr.cpe.wolodiayannis.pokemongeo.listeners;

import android.location.Location;

public interface OnLocationChangeListener {
    /**
     * On location change.
     * @param location location
     */
    void onLocationChange(Location location);
}
