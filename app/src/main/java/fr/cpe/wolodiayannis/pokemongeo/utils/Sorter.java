package fr.cpe.wolodiayannis.pokemongeo.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;

public class Sorter {

    public static HashMap<Item, Integer> sortByValue(HashMap<Item, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Item, Integer> > list =
                new LinkedList<Map.Entry<Item, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Item, Integer> >() {
            public int compare(Map.Entry<Item, Integer> o1,
                               Map.Entry<Item, Integer> o2)
            {
                return Integer.compare(o1.getKey().getId(), o2.getKey().getId());
            }

        });

        // put data from sorted list to hashmap
        HashMap<Item, Integer> temp = new LinkedHashMap<Item, Integer>();
        for (Map.Entry<Item, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
