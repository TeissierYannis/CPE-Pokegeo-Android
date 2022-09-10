package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.util.ArrayList;
import java.util.List;

public class CaughtInventory {
    private final int MAX_CAUGHTED = 1000;
    private final List<Pokemon> caughtedPokemon;

    public CaughtInventory() {
        this.caughtedPokemon = new ArrayList<>();
    }

    public Pokemon getPokemon(int index) {
        if (index < 0 || index >= MAX_CAUGHTED) {
            throw new InternalError("The index is out of the inventory");
        } else {
            if (index < caughtedPokemon.size()) {
                return caughtedPokemon.get(index);
            } else {
                return null;
            }
        }
    }

    public List<Pokemon> getPokemon() {
        return this.caughtedPokemon;
    }

    public int getItemQuantity(Pokemon pokemon) {
            return this.caughtedPokemon.get(this.caughtedPokemon.indexOf(pokemon)).getQuantity();
    }
}
