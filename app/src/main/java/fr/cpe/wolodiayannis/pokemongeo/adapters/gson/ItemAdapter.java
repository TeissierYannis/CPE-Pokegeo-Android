package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;

public class ItemAdapter extends TypeAdapter<Item> {
    @Override
    public void write(com.google.gson.stream.JsonWriter out, Item value) throws java.io.IOException {
        /*
         * {
         *   "message": "success",
         *   "data": {
         *     "id": 1,
         *     "name": "master-ball"
         *   }
         * }
         */
        out.beginObject();
        out.name("message").value("success");
        out.name("data");
        out.beginObject();
        out.name("id").value(value.getId());
        out.name("name").value(value.getName());
        out.endObject();
        out.endObject();

    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @param in the stream to read from.
     * @return the converted Java object. May be null.
     */
    @Override
    public Item read(JsonReader in) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": {
         *     "id": 1,
         *     "name": "master-ball"
         *   }
         * }
         */
        in.beginObject();
        in.nextName();
        in.nextString();
        in.nextName();
        in.beginObject();
        in.nextName();
        int id = in.nextInt();
        in.nextName();
        String name = in.nextString();
        in.endObject();
        in.endObject();
        return new Item(id, name);
    }
}
