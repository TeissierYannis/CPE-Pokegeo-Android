package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.PokemonListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

@JsonAdapter(PokemonListAdapter.class)
public class PokemonList {
    private List<Pokemon> pokemonList;

    /**
     * Constuctor.
     * @param pokemonList List of pokemons.
     */
    public PokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }
    /**
     * Get the pokemon list.
     * @return List of pokemon.
     */
    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }
}