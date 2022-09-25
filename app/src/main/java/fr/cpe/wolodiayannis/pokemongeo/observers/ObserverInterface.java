package fr.cpe.wolodiayannis.pokemongeo.observers;

public interface ObserverInterface<O, I> {
    /**
     * Set the listener.
     * @param listener listener
     */
    void setListener(I listener);

    /**
     * Get the value.
     * @return the value
     */
    O get();

    /**
     * Set the value and notify the listener.
     * @param value value
     */
    void set(O value);
}
