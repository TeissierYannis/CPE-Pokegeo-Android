package fr.cpe.wolodiayannis.pokemongeo.data;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.api.request.AbilityRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.CaughtInventoryRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.ItemInventoryRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.ItemRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.PokemonRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.StatRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.TypeRequest;
import fr.cpe.wolodiayannis.pokemongeo.api.request.UserRequest;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemBallList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemPotionList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemReviveList;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.StatList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.TypeList;

public class DataFetcher {

    /**
     * Get all pokemons.
     * @return List of pokemons.
     */
    public static PokemonList fetchPokemonList() {
        return PokemonRequest.getPokemons();
    }

    /**
     * Get all stats.
     * @return List of stats.
     */
    public static StatList fetchStatList() {
        return StatRequest.getAllStats();
    }

    /**
     * Get all types.
     * @return List of types.
     */
    public static TypeList fetchTypeList() {
        return TypeRequest.getAllTypes();
    }

    /**
     * Get all abilities.
     * @return List of abilities.
     */
    public static AbilityList fetchAbilityList() {
        return AbilityRequest.getAllAbilities();
    }

    /**
     * Get all pokemon abilities.
     * @return List of pokemon abilities.
     */
    public static HashMap<Integer, List<Integer>> fetchPokemonAbilities() {
        return Objects.requireNonNull(PokemonRequest.getPokemonAbilities()).getAbilityList();
    }

    /**
     * Get all pokemon types.
     * @return List of pokemon types.
     */
    public static HashMap<Integer, List<Integer>> fetchPokemonTypes() {
        return Objects.requireNonNull(PokemonRequest.getPokemonTypes()).getTypes();
    }

    /**
     * Get all pokemon stats.
     * @return List of pokemon stats.
     */
    public static HashMap<Integer, List<PokemonStat>> fetchPokemonStats() {
        return Objects.requireNonNull(PokemonRequest.getPokemonStat()).getPokemonStatMappingList();
    }

    /**
     * Check user is correct to login.
     * @param email User email.
     * @param password User password.
     */
    public static User checkUser(String email, String password) {
        return UserRequest.checkUser(email, password);
    }

    /**
     * Create a new user.
     * @param user User to create.
     * @param password User password.
     */
    public static User createUser(User user, String password) {
        return UserRequest.createUser(user, password);
    }

    /**
     * Get the caught inventory of a user.
     * @param userId User id.
     * @return Caught inventory.
     */
    public static CaughtInventory fetchCaughtPokemonList(int userId) {
        return CaughtInventoryRequest.getCaughtInventory(userId);
    }

    /**
     * add a caught pokemon to the user inventory.
     * @param caughtPokemon Caught pokemon to add.
     */
    public static boolean addCaughtPokemon(CaughtPokemon caughtPokemon) {
        return CaughtInventoryRequest.addCaughtPokemon(caughtPokemon);
    }

    /**
     * Update if the user is init.
     * @param userId User id.
     * @param isInit init state.
     * @return true if the update is successful.
     */
    public static boolean updateUserIsInit(int userId, boolean isInit) {
        return UserRequest.updateUserIsInit(userId, isInit);
    }

    /**
     * Get all ball list.
     * @return Ball list.
     */
    public static ItemBallList fetchItemBallList() {
        return ItemRequest.getItemBallList();
    }

    /**
     * Get all potion list.
     * @return Potion list.
     */
    public static ItemPotionList fetchItemPotionList() {
        return ItemRequest.getItemPotionList();
    }

    /**
     * Get all revive list.
     * @return Revive list.
     */
    public static ItemReviveList fetchItemReviveList() {
        return ItemRequest.getItemReviveList();
    }

    /**
     * Get the item inventory of a user.
     * @param userID User id.
     * @return Item inventory.
     */
    public static ItemInventory fetchItemInventory(int userID) {
        return ItemInventoryRequest.getItemInventory(userID);
    }

    /**
     * Init post of the user item inventory on the API.
     */
    public static void postItemInventory(ItemInventory itemInventory) {
        ItemInventoryRequest.postItemInventory(itemInventory);
    }

    /**
     * Update the user item inventory on the API.
     */
    public static void updateItemInventory(ItemInventory itemInventory) {
        ItemInventoryRequest.updateItemInventory(itemInventory);
    }
}
