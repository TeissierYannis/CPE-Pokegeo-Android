package fr.cpe.wolodiayannis.pokemongeo.listeners;

import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

public interface PokemonSwitchInterface {
    void onPokemonSwitch(Pokemon pokemon, CaughtPokemon caughtPokemon);
}
