package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.CaughtPokemonListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;

/**
 * Items inventory class.
 */
@JsonAdapter(CaughtPokemonListAdapter.class)
public class CaughtInventory implements Serializable {

    /**
     * Caught pokemon list.
     */
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
     *
     * @param caughtInventoryList The caught inventory list
     */
    public CaughtInventory(HashMap<Pokemon, CaughtPokemon> caughtInventoryList) {
        this.caughtInventoryList = caughtInventoryList;
    }

    /**
     * Get the caught inventory list.
     *
     * @return The caught inventory list.
     */
    public HashMap<Pokemon, CaughtPokemon> getCaughtInventoryList() {
        return caughtInventoryList;
    }

    /**
     * Update the life of the pokemon at the given index.
     */
    public void updateCaughtPokemonLife(Pokemon pokemon, int life) {
        Objects.requireNonNull(caughtInventoryList.get(pokemon)).setCurrentLifeState(life);
    }

    public ArrayList<?> getPokemonAndCaughtPokemon(Pokemon pokemon) {
        ArrayList<Object> pokemonAndCaughtPokemon = new ArrayList<>();
        pokemonAndCaughtPokemon.add(pokemon);
        pokemonAndCaughtPokemon.add(caughtInventoryList.get(pokemon));
        return pokemonAndCaughtPokemon;
    }

    /**
     * Get caught pokemon by pokemon id.
     *
     * @param pokemonID The pokemon id.
     * @return The caught pokemon.
     */
    public CaughtPokemon getCaughtPokemonFromPokemonID(int pokemonID) {
        for (Pokemon pokemon : caughtInventoryList.keySet()) {
            if (pokemon.getId() == pokemonID) {
                return caughtInventoryList.get(pokemon);
            }
        }
        return null;
    }


    /**
     * Add a pokemon to the caught inventory list.
     *
     * @param pokemon       The pokemon to add.
     * @param caughtPokemon The caught pokemon to add.
     */
    public void addPokemon(Pokemon pokemon, CaughtPokemon caughtPokemon) {
        this.caughtInventoryList.put(pokemon, caughtPokemon);
    }

    /**
     * Add a pokemon to the caught inventory list.
     *
     * @param pokemon The pokemon to add.
     */
    public CaughtPokemon addPokemon(Pokemon pokemon) {

        CaughtPokemon caughtPokemon = new CaughtPokemon(
                Datastore.getInstance().getUser().getId(),
                pokemon.getId(),
                0,
                pokemon.getHp(),
                new Timestamp(System.currentTimeMillis())
        );

        this.caughtInventoryList.put(
                pokemon,
                caughtPokemon
        );

        return caughtPokemon;
    }

    /**
     * From pokemonID get the pokemon index in the inventory
     *
     * @param pokemonID The pokemon ID
     * @return The pokemon index in the inventory
     */
    public int getPokemonIndex(int pokemonID) {
        int index = 0;
        for (Pokemon pokemon : caughtInventoryList.keySet()) {
            if (pokemon.getId() == pokemonID) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Get the pokemon by ID in the caught inventory list.
     *
     * @param id The pokemon ID
     * @return The pokemon
     */
    public Pokemon getPokemonById(int id) {
        for (Pokemon pokemon : caughtInventoryList.keySet()) {
            if (pokemon.getId() == id) {
                return pokemon;
            }
        }
        return null;
    }

    /**
     * Get the pokemon at the given index.
     *
     * @param index The pokemon index
     */
    public Pokemon getCaughtPokemon(int index) {
        return (Pokemon) caughtInventoryList.keySet().toArray()[index];
    }

    /**
     * Check if two items are equals.
     *
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
}
