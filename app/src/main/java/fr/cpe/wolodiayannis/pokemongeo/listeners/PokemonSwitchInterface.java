package fr.cpe.wolodiayannis.pokemongeo.listeners;

import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

public interface PokemonSwitchInterface {
    /**
     * On pokemon switch.
     *
     * @param pokemon       pokemon
     * @param caughtPokemon caught pokemon
     */
    void onPokemonSwitch(Pokemon pokemon, CaughtPokemon caughtPokemon);
}
