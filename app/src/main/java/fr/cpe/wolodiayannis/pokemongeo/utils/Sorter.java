package fr.cpe.wolodiayannis.pokemongeo.utils;

import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;

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
