package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.util.ArrayList;
import java.util.List;

public class CaughtInventory {
    private final List<Pokemon> caughtPokemonInventory;

    public CaughtInventory() {
        this.caughtPokemonInventory = new ArrayList<>();
    }


    public Pokemon getCaughtPokemon(int index) {
        if (index < 0 || index >= this.caughtPokemonInventory.size()) {
            throw new InternalError("The index is out of the caught inventory");
        } else {
            return caughtPokemonInventory.get(index);
        }
    }

    public List<Pokemon> getCaughtPokemon() {
        return this.caughtPokemonInventory;
    }

    public CaughtInventory addCaughtPokemon(Pokemon pokemon, int quantity) {
        if (this.pokemonIsCaught(pokemon)) {
            this.caughtPokemonInventory.get(this.caughtPokemonInventory.indexOf(pokemon)).addQuantity(quantity);
        } else {
            pokemon.addQuantity(quantity);
            this.caughtPokemonInventory.add(pokemon);
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


    private boolean pokemonIsCaught(Pokemon pokemon) {
        for (Pokemon i : caughtPokemonInventory) {
            if (i.getName().equals(pokemon.getName())) {
                return true;
            }
        }
        return false;
    }
}
