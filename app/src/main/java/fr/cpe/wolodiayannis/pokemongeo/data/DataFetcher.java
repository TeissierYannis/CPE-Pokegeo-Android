package fr.cpe.wolodiayannis.pokemongeo.data;

import java.io.Serializable;

import fr.cpe.wolodiayannis.pokemongeo.api.request.AbilityRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.ItemRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.PokemonRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.StatRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.TypeRequest;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonStatList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.StatList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.TypeList;

public class DataFetcher {

    public static PokemonList fetchPokemonList() {
        return PokemonRequest.getPokemons();
    }

    public static ItemList fetchItemList() {
        return ItemRequest.getAllItems();
    }

    public static StatList fetchStatList() {
        return StatRequest.getAllStats();
    }

    public static TypeList fetchTypeList() {
        return TypeRequest.getAllTypes();
    }

    public static AbilityList fetchAbilityList() {
        return AbilityRequest.getAllAbilities();
    }

    public static AbilityList fetchPokemonAbilities(int id) {
        return PokemonRequest.getPokemonAbility(id);
    }

    public static TypeList fetchPokemonTypes(int id) {
        return PokemonRequest.getPokemonType(id);
    }

    public static PokemonStatList fetchPokemonStats(int id) {
        return PokemonRequest.getPokemonStat(id);
    }
}
