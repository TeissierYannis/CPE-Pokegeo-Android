package fr.cpe.wolodiayannis.pokemongeo.observers;

import android.location.Location;

import fr.cpe.wolodiayannis.pokemongeo.listeners.OnLocationChangeListener;

/**
 * LocationObserver.
 */
public class LocationObserver {

    /**
     * Listener.
     */
    private OnLocationChangeListener listener;

    /**
     * Location.
     */
    private Location value;

    /**
     * Set the listener.
     * @param listener listener
     */
    public void setListener(OnLocationChangeListener listener) {
        this.listener = listener;
    }

    /**
     * Get the value.
     * @return the value
     */
    public Location get() {
        return value;
    }

    /**
     * Set the value and notify the listener.
     * @param value value
     */
    public void set(Location value) {
        this.value = value;
        if (listener != null) {
            listener.onLocationChange(value);
        }
    }
}
