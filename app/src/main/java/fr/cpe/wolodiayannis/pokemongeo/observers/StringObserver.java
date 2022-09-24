package fr.cpe.wolodiayannis.pokemongeo.observers;

import fr.cpe.wolodiayannis.pokemongeo.listeners.OnStringChangeListener;

public class StringObserver {

    private OnStringChangeListener listener;

    private String value;

    public void setListener(OnStringChangeListener listener) {
        this.listener = listener;
    }

    public String get() {
        return value;
    }

    public void set(String value) {
        this.value = value;
        if (listener != null) {
            listener.onStringChange(value);
        }
    }
}
