package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;

public class ItemListAdapter extends TypeAdapter<ItemList> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, ItemList value) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": [
         *     {
         *       "id": 1,
         *       "name": "master-ball"
         *     },
         *     {
         *       "id": 2,
         *       "name": "ultra-ball"
         *     }
         *   ]
         * }
         */
        out.beginObject();
        out.name("message").value("success");
        out.name("data");
        out.beginArray();
        for (int i = 0; i < value.getItemList().size(); i++) {
            out.beginObject();
            out.name("id").value(value.getItemList().get(i).getId());
            out.name("name").value(value.getItemList().get(i).getName());
            out.endObject();
        }
        out.endArray();
        out.endObject();
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @param in the stream to read from
     * @return the converted Java object. May be null.
     */
    @Override
    public ItemList read(JsonReader in) throws IOException {
        /*
         * {
         *  "message": "ok",
         *  "data": [
         *    {
         *    "id": 1,
         *    "name": "Potion",
         *    "price": 300
         *    },
         *    {
         *    "id": 2,
         *    "name": "Super Potion",
         *    "price": 700
         *    }
         *    ]
         *    }
         */
        List<Item> itemList = new ArrayList<>();
        in.beginObject();
        in.nextName();
        String message = in.nextString();
        if (message.equals("success")) {

            in.nextName();
            in.beginArray();
            while (in.hasNext()) {
                in.beginObject();
                in.nextName();
                int id = in.nextInt();
                in.nextName();
                String name = in.nextString();
                in.nextName();
                int price = in.nextInt();

                in.endObject();
                itemList.add(new Item(id, name, price));
            }
            in.endArray();
            in.endObject();
            return new ItemList(itemList);
        } else {
            throw new IOException("Cannot fetch item list");
        }
    }
}
