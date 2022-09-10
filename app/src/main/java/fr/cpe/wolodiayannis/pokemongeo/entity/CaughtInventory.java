package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.util.ArrayList;
import java.util.List;

public class CaughtInventory {
    private final int MAX_CAUGHT = 1000;
    private final List<Pokemon> caughtPokemonInventory;

    public CaughtInventory() {
        this.caughtPokemonInventory = new ArrayList<>();
    }

    public Pokemon getCaughtPokemon(int index) {
        if (index < 0 || index >= MAX_CAUGHT) {
            throw new InternalError("The index is out of the caught inventory");
        } else {
            if (index < caughtPokemonInventory.size()) {
                return caughtPokemonInventory.get(index);
            } else {
                return null;
            }
        }
    }

    public List<Pokemon> getCaughtPokemon() {
        return this.caughtPokemonInventory;
    }

    public CaughtInventory addCaughtPokemon(Pokemon pokemon, int quantity) {
        if (this.pokemonIsCaught(pokemon)) {
            this.caughtPokemonInventory.get(this.caughtPokemonInventory.indexOf(pokemon)).addQuantity(quantity);
        } else {
            if (!this.caughtPokemonInventoryIsFull()) {
                pokemon.addQuantity(quantity);
                this.caughtPokemonInventory.add(pokemon);
            }
        }
        return this;
    }

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

    public int getCaughtPokemonQuantity(Pokemon pokemon) {
        if (this.pokemonIsCaught(pokemon)) {
            return this.caughtPokemonInventory.get(this.caughtPokemonInventory.indexOf(pokemon)).getQuantity();
        }
        throw new RuntimeException("The pokemon wasn't caught");
    }

    private boolean caughtPokemonInventoryIsFull() {
        if (this.caughtPokemonInventory.size() >= MAX_CAUGHT) {
            throw new RuntimeException("The caught inventory is full");
        } else {
            return false;
        }
    }

    private boolean pokemonIsCaught(Pokemon pokemon) {
        for (Pokemon i : caughtPokemonInventory) {
            if (i.getName().equals(pokemon.getName())) {
                return true;
            }
        }
        return false;
    }
}
