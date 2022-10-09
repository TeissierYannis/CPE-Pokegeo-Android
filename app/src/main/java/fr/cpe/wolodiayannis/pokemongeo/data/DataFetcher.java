package fr.cpe.wolodiayannis.pokemongeo.data;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.api.request.AbilityRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.CaughtInventoryRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.ItemRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.PokemonRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.StatRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.TypeRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.UserRequest;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemRevive;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemBallList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemPotionList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemReviveList;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.StatList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.TypeList;

public class DataFetcher {

    public static PokemonList fetchPokemonList() {
        return PokemonRequest.getPokemons();
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

    public static CaughtInventory fetchCaughtPokemonList(int userId) {
        return CaughtInventoryRequest.getCaughtInventory(userId);
    }

    public static boolean addCaughtPokemon(CaughtPokemon caughtPokemon) {
        return CaughtInventoryRequest.addCaughtPokemon(caughtPokemon);
    }

    public static boolean updateUserIsInit(int userId, boolean isInit) {
        return UserRequest.updateUserIsInit(userId, isInit);
    }

    public static ItemBallList fetchItemBallList() {
        return ItemRequest.getItemBallList();
    }

    public static ItemPotionList fetchItemPotionList() {
        return ItemRequest.getItemPotionList();
    }

    public static ItemReviveList fetchItemReviveList() {
        return ItemRequest.getItemReviveList();
    }
}
