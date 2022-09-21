package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

public class PokemonList {
    private List<Pokemon> pokemonList;

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