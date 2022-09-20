package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;

import java.util.HashMap;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.ItemInventory;

public class ItemsInventoryAdapter extends TypeAdapter<ItemInventory> {

    @Override
    public void write(com.google.gson.stream.JsonWriter out, ItemInventory value) throws java.io.IOException {

    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     * @param in the stream to read from.
     * @return the converted Java object. May be null.
     */
    @Override
    public ItemInventory read(com.google.gson.stream.JsonReader in) throws java.io.IOException {
        /*
         * {
         *   "message": "success",
         *   "data": {
         *     "id": 1,
         *     "name": "master-ball",
         *     "quantity": 1
         *   }
         * }
         */

        HashMap<Item, Integer> items = new HashMap<>();

        in.beginObject();
        in.nextName();
        in.nextString();
        in.nextName();

        // for each object
        while (in.peek() != JsonToken.END_OBJECT) {
            in.beginObject();
            in.nextName();
            int id = in.nextInt();
            in.nextName();
            String name = in.nextString();
            in.nextName();
            int quantity = in.nextInt();
            in.endObject();
            in.nextName();
            items.put(new Item(id, name), quantity);
        }

        in.endObject();

        return new ItemInventory(items);
    }
}
