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

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;

public class Sorter {

    /**
     * Sort by value.
     * @param hm Map to sort.
     * @return Sorted map.
     */
    public static HashMap<Item, Integer> sortByValue(HashMap<Item, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Item, Integer> > list =
                new LinkedList<Map.Entry<Item, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, (o1, o2) -> Integer.compare(o1.getKey().getId(), o2.getKey().getId()));

        // put data from sorted list to hashmap
        HashMap<Item, Integer> temp = new LinkedHashMap<Item, Integer>();
        for (Map.Entry<Item, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /**
     * Sort by distance.
     * @param hm Map to sort.
     * @param userLocation User location.
     * @return Sorted map.
     */
    public static HashMap<Pokemon, GeoPoint> sortByPokemonDistance(HashMap<Pokemon, GeoPoint> hm, GeoPoint userLocation) {
        // Create a list from elements of HashMap
        List<Map.Entry<Pokemon, GeoPoint> > list =
                new LinkedList<>(hm.entrySet());

        // Sort the list
        Collections.sort(list, (o1, o2) -> Double.compare(o1.getValue().distanceToAsDouble(userLocation), o2.getValue().distanceToAsDouble(userLocation)));

        // put data from sorted list to hashmap
        HashMap<Pokemon, GeoPoint> temp = new LinkedHashMap<>();
        for (Map.Entry<Pokemon, GeoPoint> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /**
     * Sort by distance.
     * @param shops List of shops.
     * @param userLocation User location.
     * @return Sorted list.
     */
    public static List<POI> sortPOIByDistance(ArrayList<POI> shops, GeoPoint userLocation) {
        // Create a list from elements of HashMap
        List<POI> list = shops;

        // Sort the list
        Collections.sort(list, new Comparator<POI>() {
            public int compare(POI o1,
                               POI o2)
            {
                return Double.compare(o1.mLocation.distanceToAsDouble(userLocation), o2.mLocation.distanceToAsDouble(userLocation));
            }
        });

        return list;
    }
}
