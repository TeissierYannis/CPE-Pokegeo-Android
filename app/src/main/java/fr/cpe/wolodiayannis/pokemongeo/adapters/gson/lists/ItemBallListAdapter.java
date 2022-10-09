package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemBall;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemBallList;

public class ItemBallListAdapter extends TypeAdapter<ItemBallList> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, ItemBallList value) throws IOException {
        /*
        {
            "message": "success",
            "data": [
                {
                  "id": 0,
                  "name": "honor-ball",
                  "price": 100,
                  "accuracy": 10
                },
                {
                  "id": 1,
                  "name": "poke-ball",
                  "price": 100,
                  "accuracy": 10
                },
                ...
            ]
        }
        */
        out.beginObject();
        out.name("message").value("success");
        out.name("data");
        out.beginArray();
        for (int i = 0; i < value.getItemBallList().size(); i++) {
            out.beginObject();
            out.name("id").value(value.getItemBallList().get(i).getId());
            out.name("name").value(value.getItemBallList().get(i).getName());
            out.name("price").value(value.getItemBallList().get(i).getPrice());
            out.name("accuracy").value(value.getItemBallList().get(i).getAccuracy());
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
    public ItemBallList read(JsonReader in) throws IOException {
        /*
        {
            "message": "success",
            "data": [
                {
                  "id": 0,
                  "name": "honor-ball",
                  "price": 100,
                  "accuracy": 10
                },
                {
                  "id": 1,
                  "name": "poke-ball",
                  "price": 100,
                  "accuracy": 10
                },
                ...
            ]
        }
        */

        List<ItemBall> itemBallList = new ArrayList<>();
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
                int accuracy = in.nextInt();
                in.endObject();

                itemBallList.add(new ItemBall(
                        id,
                        name,
                        price,
                        accuracy
                ));
            }
            in.endArray();
            in.endObject();
            return new ItemBallList(itemBallList);
        } else {
            throw new IOException("Cannot fetch item ball list");
        }
    }
}
