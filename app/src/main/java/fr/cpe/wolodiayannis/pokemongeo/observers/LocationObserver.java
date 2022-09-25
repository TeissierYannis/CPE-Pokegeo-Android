package fr.cpe.wolodiayannis.pokemongeo.observers;

import android.location.Location;

import fr.cpe.wolodiayannis.pokemongeo.listeners.OnLocationChangeListener;

/**
 * LocationObserver.
 */
public class LocationObserver implements ObserverInterface<Location, OnLocationChangeListener> {

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
     *
     * @param listener listener
     */
    @Override
    public void setListener(OnLocationChangeListener listener) {
        this.listener = listener;
    }

    /**
     * Get the value.
     *
     * @return the value
     */
    @Override
    public Location get() {
        return value;
    }

    /**
     * Set the value and notify the listener.
     *
     * @param value value
     */
    @Override
    public void set(Location value) {
        this.value = value;
        if (listener != null) {
            listener.onLocationChange(value);
        }
    }
}
