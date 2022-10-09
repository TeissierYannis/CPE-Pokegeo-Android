package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemRevive;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemReviveList;

public class ItemReviveListAdapter extends TypeAdapter<ItemReviveList> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, ItemReviveList value) throws IOException {
        /*
        {
            "message": "success",
                "data": [
            {
                "id": 9,
                "name": "revive",
                "price": 2000,
                "hp_amount": 50
            },
            {
                "id": 10,
                "name": "max-revive",
                "price": 5000,
                "hp_amount": 100
            }
          ]
        }
        */

        out.beginObject();
        out.name("message").value("success");
        out.name("data");
        out.beginArray();
        for (int i = 0; i < value.getItemReviveList().size(); i++) {
            out.beginObject();
            out.name("id").value(value.getItemReviveList().get(i).getId());
            out.name("name").value(value.getItemReviveList().get(i).getName());
            out.name("price").value(value.getItemReviveList().get(i).getPrice());
            out.name("hp_amount").value(value.getItemReviveList().get(i).getHpAmount());
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
    public ItemReviveList read(JsonReader in) throws IOException {
        /*
        {
            "message": "success",
                "data": [
            {
                "id": 9,
                "name": "revive",
                "price": 2000,
                "hp_amount": 50
            },
            {
                "id": 10,
                "name": "max-revive",
                "price": 5000,
                "hp_amount": 100
            }
          ]
        }
        */

        List<ItemRevive> itemReviveList = new ArrayList<>();
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
                int hp_amount = in.nextInt();
                in.endObject();

                itemReviveList.add(new ItemRevive(
                        id,
                        name,
                        price,
                        hp_amount
                ));
            }
            in.endArray();
            in.endObject();
            return new ItemReviveList(itemReviveList);
        } else {
            throw new IOException("Cannot fetch item ball list");
        }
    }
}
