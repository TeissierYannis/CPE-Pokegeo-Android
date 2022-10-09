package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemPotion;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemPotionList;

public class ItemPotionListAdapter extends TypeAdapter<ItemPotionList> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, ItemPotionList value) throws IOException {
        /*
        {
            "message": "success",
                "data": [
                {
                    "id": 5,
                    "name": "potion",
                    "price": 300,
                    "stat_id": 1,
                    "bonus": 20
                },
                {
                    "id": 6,
                    "name": "super-potion",
                    "price": 700,
                    "stat_id": 1,
                    "bonus": 50
                },
                ...
            ]
       }
       */

        out.beginObject();
        out.name("message").value("success");
        out.name("data");
        out.beginArray();
        for (int i = 0; i < value.getItemPotionList().size(); i++) {
            out.beginObject();
            out.name("id").value(value.getItemPotionList().get(i).getId());
            out.name("name").value(value.getItemPotionList().get(i).getName());
            out.name("price").value(value.getItemPotionList().get(i).getPrice());
            out.name("stat_id").value(value.getItemPotionList().get(i).getStat().getId());
            out.name("bonus").value(value.getItemPotionList().get(i).getBonus());
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
    public ItemPotionList read(JsonReader in) throws IOException {
                /*
        {
            "message": "success",
                "data": [
                {
                    "id": 5,
                    "name": "potion",
                    "price": 300,
                    "stat_id": 1,
                    "bonus": 20
                },
                {
                    "id": 6,
                    "name": "super-potion",
                    "price": 700,
                    "stat_id": 1,
                    "bonus": 50
                },
                ...
            ]
       }
       */

        List<ItemPotion> itemPotionList = new ArrayList<>();
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
                in.nextName();
                int stat_id = in.nextInt();
                in.nextName();
                int bonus = in.nextInt();
                in.endObject();

                itemPotionList.add(new ItemPotion(
                        id,
                        name,
                        price,
                        stat_id,
                        bonus
                ));
            }
            in.endArray();
            in.endObject();
            return new ItemPotionList(itemPotionList);
        } else {
            throw new IOException("Cannot fetch item potion list");
        }
    }
}
