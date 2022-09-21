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

    public CaughtInventory() {
        this.caughtInventoryList = new HashMap<>();
    }

    public CaughtInventory(HashMap<Pokemon, Integer> caughtInventoryList) {
        this.caughtInventoryList = caughtInventoryList;
    }

    public HashMap<Pokemon, Integer> getCaughtInventoryList() {
        return caughtInventoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaughtInventory)) return false;
        CaughtInventory that = (CaughtInventory) o;
        return caughtInventoryList.equals(that.caughtInventoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caughtInventoryList);
    }

    public Pokemon getPokemonById(int id) {
        for (Pokemon pokemon : caughtInventoryList.keySet()) {
            if (pokemon.getId() == id) {
                return pokemon;
            }
        }
        return null;
    }
}
