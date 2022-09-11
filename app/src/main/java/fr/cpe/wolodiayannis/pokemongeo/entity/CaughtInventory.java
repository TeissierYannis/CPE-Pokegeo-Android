package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * CaughtInventory class.
 */
@DatabaseTable(tableName = "caught_inventory")
public class CaughtInventory {
    /**
     * List of caught pokemon.
     */
    private final List<Pokemon> caughtPokemonInventory;

    /**
     * Constructor.
     * Init the list of caught pokemon.
     */
    public CaughtInventory() {
        this.caughtPokemonInventory = new ArrayList<>();
    }

    /**
     * Get a pokemon by is position in the list.
     * @param index index of the pokemon in the list.
     * @throws InternalError if the index is out of bound.
     * @return the pokemon.
     */
    public Pokemon getCaughtPokemon(int index) {
        if (index < 0 || index >= this.caughtPokemonInventory.size()) {
            throw new InternalError("The index is out of the caught inventory");
        } else {
            return caughtPokemonInventory.get(index);
        }
    }

    /**
     * Get the list of caught pokemon.
     * @return the list of caught pokemon.
     */
    public List<Pokemon> getCaughtPokemon() {
        return this.caughtPokemonInventory;
    }

    /**
     * Add a pokemon to the list of caught pokemon.
     * @param pokemon the pokemon to add.
     * @param quantity the quantity of the pokemon to add.
     * @return instance of the class for chaining.
     */
    public CaughtInventory addCaughtPokemon(Pokemon pokemon, int quantity) {
        if (this.pokemonIsCaught(pokemon)) {
            this.caughtPokemonInventory.get(this.caughtPokemonInventory.indexOf(pokemon)).addQuantity(quantity);
        } else {
            pokemon.addQuantity(quantity);
            this.caughtPokemonInventory.add(pokemon);
        }
        return this;
    }

    /**
     * Remove a pokemon from the list of caught pokemon.
     * @param pokemon the pokemon to remove.
     * @param quantity the quantity of the pokemon to remove.
     * @throws RuntimeException if the pokemon is not caught.
     * @return instance of the class for chaining.
     */
    public CaughtInventory removeCaughtPokemon(Pokemon pokemon, int quantity) {
        if (this.pokemonIsCaught(pokemon)) {
            this.caughtPokemonInventory.get(this.caughtPokemonInventory.indexOf(pokemon)).removeQuantity(quantity);
            if (this.caughtPokemonInventory.get(this.caughtPokemonInventory.indexOf(pokemon)).getQuantity() == 0) {
                this.caughtPokemonInventory.remove(pokemon);
            }
        } else {
            throw new RuntimeException("The pokemon wasn't caught");
        }
        return this;
    }

    /**
     * Get pokemon quantity.
     * @param pokemon the pokemon.
     * @throws RuntimeException if the pokemon is not caught.
     * @return the quantity of the pokemon.
     */
    public int getCaughtPokemonQuantity(Pokemon pokemon) {
        if (this.pokemonIsCaught(pokemon)) {
            return this.caughtPokemonInventory.get(this.caughtPokemonInventory.indexOf(pokemon)).getQuantity();
        }
        throw new RuntimeException("The pokemon wasn't caught");
    }

    /**
     * Check if a pokemon is caught.
     * @param pokemon the pokemon.
     * @return true if the pokemon is caught, false otherwise.
     */
    private boolean pokemonIsCaught(Pokemon pokemon) {
        for (Pokemon i : caughtPokemonInventory) {
            if (i.getName().equals(pokemon.getName())) {
                return true;
            }
        }
        return false;
    }
}
