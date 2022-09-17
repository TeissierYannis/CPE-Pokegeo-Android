package fr.cpe.wolodiayannis.pokemongeo.datas;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.ItemsInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.entity.UserPokedex;

public class UserDatas {
    private static User user;
    private static List<ItemsInventory> itemsInventory;
    private static List<CaughtInventory> caughtInventory;
    private static List<UserPokedex> userPokedex;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserDatas.user = user;
    }

    public static List<ItemsInventory> getItemsInventory() {
        return itemsInventory;
    }

    public static void addItemInventory(ItemsInventory item) {
        itemsInventory.add(item);
    }

    public static List<CaughtInventory> getCaughtInventory() {
        return caughtInventory;
    }

    public static void addCaughtInventory(CaughtInventory pokemon) {
        caughtInventory.add(pokemon);
    }

    public static List<Pokemon> getCaughtPokemonList() {
        List<Pokemon> caughtPokemonList = new ArrayList<Pokemon>();
        for (CaughtInventory pokemon : caughtInventory) {
            caughtPokemonList.add(pokemon.getPokemon());
        }
        return caughtPokemonList;
    }

    public static List<UserPokedex> getUserPokedex() {
        return userPokedex;
    }

    public static void addUserPokedex(UserPokedex pokemon) {
        userPokedex.add(pokemon);
    }
}


