package fr.cpe.wolodiayannis.pokemongeo.listeners;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

/**
 * Interface for the pokedex listener
 */
public interface PokedexListenerInterface {
    /**
     * On select pokemon.
     * @param pokemon pokemon
     */
    void onPokemonSelected(Pokemon pokemon);
}

