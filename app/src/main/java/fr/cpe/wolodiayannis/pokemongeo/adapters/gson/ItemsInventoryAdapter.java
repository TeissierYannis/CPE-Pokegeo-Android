package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;

import java.util.HashMap;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.ItemsInventory;

public class ItemsInventoryAdapter extends TypeAdapter<ItemsInventory> {

    @Override
    public void write(com.google.gson.stream.JsonWriter out, ItemsInventory value) throws java.io.IOException {

    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     * @param in the stream to read from.
     * @return the converted Java object. May be null.
     */
    @Override
    public ItemsInventory read(com.google.gson.stream.JsonReader in) throws java.io.IOException {
        /*
         * {
         *   "message": "success",
         *   "data": {
         *     "id": 1,
         *     "name": "Potion",
         *     "quantity": 1
         *   }
         * }
         */

        return null;
    }
}
