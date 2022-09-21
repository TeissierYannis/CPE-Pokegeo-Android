package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

public class PokemonList {
    private List<Pokemon> pokemonList;

    /**
     * Get the pokemon list.
     * @return List of pokemon.
     */
    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }
}