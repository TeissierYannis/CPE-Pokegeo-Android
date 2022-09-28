package fr.cpe.wolodiayannis.pokemongeo.data;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.api.request.AbilityRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.ItemRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.PokemonRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.StatRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.TypeRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.UserRequest;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
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

    public static HashMap<Integer, List<Integer>> fetchPokemonAbilities() {
        return Objects.requireNonNull(PokemonRequest.getPokemonAbilities()).getAbilityList();
    }

    public static HashMap<Integer, List<Integer>> fetchPokemonTypes() {
        return Objects.requireNonNull(PokemonRequest.getPokemonTypes()).getTypes();
    }

    public static HashMap<Integer, List<PokemonStat>> fetchPokemonStats() {
        return Objects.requireNonNull(PokemonRequest.getPokemonStat()).getPokemonStatMappingList();
    }

    public static User checkUser(String email, String password) {
        return UserRequest.checkUser(email, password);
    }

    public static User createUser(User user, String password) {
        return UserRequest.createUser(user, password);
    }

    public static HashMap<Pokemon, CaughtPokemon> fetchCaughtPokemons(int userId) {
        // TODO
        // return UserRequest.getCaughtPokemons(userId);
        return null;
    }

    public static void addCaughtPokemon(CaughtPokemon caughtPokemon) {
        // TODO
        // UserRequest.addCaughtPokemon(caughtPokemon);
    }
}
