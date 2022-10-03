package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.CaughtPokemonListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;

/**
 * Items inventory class.
 */
@JsonAdapter(CaughtPokemonListAdapter.class)
public class CaughtInventory implements Serializable {

    @SerializedName("data")
    private HashMap<Pokemon, CaughtPokemon> caughtInventoryList;

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
    public CaughtInventory(HashMap<Pokemon, CaughtPokemon> caughtInventoryList) {
        this.caughtInventoryList = caughtInventoryList;
    }

    /**
     * Get the caught inventory list.
     * @return The caught inventory list.
     */
    public HashMap<Pokemon, CaughtPokemon> getcaughtInventoryList() {
        return caughtInventoryList;
    }


    /**
     * Add a pokemon to the caught inventory list.
     * @param pokemon The pokemon to add.
     * @param caughtPokemon The caught pokemon to add.
     */
    public void addPokemon(Pokemon pokemon, CaughtPokemon caughtPokemon) {
        this.caughtInventoryList.put(pokemon, caughtPokemon);
    }

    /**
     * Add a pokemon to the caught inventory list.
     * @param pokemon The pokemon to add.
     */
    public void addPokemon(Pokemon pokemon, int user_id) {

        CaughtPokemon caughtPokemon = new CaughtPokemon(
                user_id,
                pokemon.getId(),
                0,
                pokemon.getHp(),
                new Timestamp(System.currentTimeMillis())
        );

        this.caughtInventoryList.put(
                pokemon,
                caughtPokemon
        );
        try {
            new Thread(() -> DataFetcher.addCaughtPokemon(caughtPokemon));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
