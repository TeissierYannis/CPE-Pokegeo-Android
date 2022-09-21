package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.ItemsInventoryAdapter;

/**
 * Items inventory class.
 */
@JsonAdapter(ItemsInventoryAdapter.class)
public class CaughtInventory {

    @SerializedName("data")
    private HashMap<Pokemon, Integer> caughtInventoryList;

    /**
     * Constructor.
     * Create the a new empty HashMap.
     */
    public CaughtInventory() {
        this.caughtInventoryList = new HashMap<>();
    }

    /**
     * Constructor.
     * set the caught inventory HashMap.
     * @param caughtInventoryList The caught inventory list
     */
    public CaughtInventory(HashMap<Pokemon, Integer> caughtInventoryList) {
        this.caughtInventoryList = caughtInventoryList;
    }

    /**
     * Get the caught inventory list.
     * @return The caught inventory list.
     */
    public HashMap<Pokemon, Integer> getCaughtInventoryList() {
        return caughtInventoryList;
    }

    /**
     * Check if two items are equals.
     * @param o Item to compare.
     * @return True if equals, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaughtInventory)) return false;
        CaughtInventory that = (CaughtInventory) o;
        return caughtInventoryList.equals(that.caughtInventoryList);
    }

    /**
     * Hash the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(caughtInventoryList);
    }

    /**
     * Get the pokemon by ID in the caught inventory list.
     */
    public Pokemon getPokemonById(int id) {
        for (Pokemon pokemon : caughtInventoryList.keySet()) {
            if (pokemon.getId() == id) {
                return pokemon;
            }
        }
        return null;
    }
}
