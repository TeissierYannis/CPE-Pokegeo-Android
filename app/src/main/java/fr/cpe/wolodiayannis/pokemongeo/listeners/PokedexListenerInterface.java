package fr.cpe.wolodiayannis.pokemongeo.listeners;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

/**
 * Interface for the pokedex listener
 */
public interface PokedexListenerInterface {
    void onPokemonSelected(Pokemon pokemon);
}

