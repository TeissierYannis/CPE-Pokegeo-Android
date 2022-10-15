package fr.cpe.wolodiayannis.pokemongeo.utils;

import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;

public class Sorter {

    /**
     * Sort by value.
     *
     * @param hm Map to sort.
     * @return Sorted map.
     */
    public static HashMap<Item, Integer> sortByValue(HashMap<Item, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Item, Integer>> list =
                new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort(Comparator.comparingInt(o -> o.getKey().getId()));

        // put data from sorted list to hashmap
        HashMap<Item, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<Item, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /**
     * Sort item inventory by item id.
     *
     * @param itemInventory Item inventory to sort.
     * @return Sorted item inventory.
     */
    public static ItemInventory sortItemInventory(ItemInventory itemInventory) {
        List<Map.Entry<Item, Integer>> list = new LinkedList<>(sortByValue(itemInventory.getItemIventoryList()).entrySet());

        // reverse the order
        Collections.reverse(list);

        HashMap<Item, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<Item, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return new ItemInventory(temp);
    }

    /**
     * Sort caught inventory by pokemon id.
     *
     * @param caughtInventory CaughtInventory to sort.
     * @return Sorted caught inventory.
     */
    public static CaughtInventory sortCaughtInventory(CaughtInventory caughtInventory) {

        // Create a list from elements of HashMap
        List<Map.Entry<Pokemon, CaughtPokemon>> list = new LinkedList<>(caughtInventory.getCaughtInventoryList().entrySet());

        // Sort the list
        list.sort(Comparator.comparingInt(o -> o.getKey().getId()));

        // put data from sorted list to hashmap
        HashMap<Pokemon, CaughtPokemon> temp = new LinkedHashMap<>();
        for (Map.Entry<Pokemon, CaughtPokemon> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return new CaughtInventory(temp);
    }

    /**
     * Sort by distance.
     *
     * @param hm           Map to sort.
     * @param userLocation User location.
     * @return Sorted map.
     */
    public static HashMap<Pokemon, GeoPoint> sortByPokemonDistance(HashMap<Pokemon, GeoPoint> hm, GeoPoint userLocation) {
        // Create a list from elements of HashMap
        List<Map.Entry<Pokemon, GeoPoint>> list =
                new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort(Comparator.comparingDouble(o -> o.getValue().distanceToAsDouble(userLocation)));

        // put data from sorted list to hashmap
        HashMap<Pokemon, GeoPoint> temp = new LinkedHashMap<>();
        for (Map.Entry<Pokemon, GeoPoint> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /**
     * Sort by distance.
     *
     * @param shops        List of shops.
     * @param userLocation User location.
     * @return Sorted list.
     */
    public static List<POI> sortPOIByDistance(ArrayList<POI> shops, GeoPoint userLocation) {
        // Create a list from elements of HashMap

        // Sort the list
        shops.sort(Comparator.comparingDouble(o -> o.mLocation.distanceToAsDouble(userLocation)));

        return shops;
    }
}
