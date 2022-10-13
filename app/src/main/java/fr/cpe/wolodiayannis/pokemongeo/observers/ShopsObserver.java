package fr.cpe.wolodiayannis.pokemongeo.observers;

import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

import fr.cpe.wolodiayannis.pokemongeo.listeners.OnShopsChangeListener;

/**
 * LocationObserver.
 */
public class ShopsObserver implements ObserverInterface<ArrayList<POI>, OnShopsChangeListener> {

    /**
     * Listener.
     */
    private OnShopsChangeListener listener;

    /**
     * Location.
     */
    private ArrayList<POI> value;

    /**
     * Set the listener.
     *
     * @param listener listener
     */
    @Override
    public void setListener(OnShopsChangeListener listener) {
        this.listener = listener;
    }

    /**
     * Get the value.
     *
     * @return the value
     */
    @Override
    public ArrayList<POI> get() {
        return value;
    }

    /**
     * Set the value and notify the listener.
     *
     * @param value value
     */
    @Override
    public void set(ArrayList<POI> value) {
        this.value = value;
        if (listener != null) {
            if (value != null) {
                listener.onShopsChange(value);
            }
        }
    }

    /**
     * On need update.
     * @param position position
     */
    public void  needUpdate(GeoPoint position) {
        if (listener != null) {
            listener.onShopsNeedUpdate(position);
        }
    }
}
