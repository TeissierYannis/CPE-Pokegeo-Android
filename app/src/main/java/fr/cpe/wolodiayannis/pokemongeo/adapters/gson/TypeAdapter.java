package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;

public class TypeAdapter extends com.google.gson.TypeAdapter<Type> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out  the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, Type value) throws IOException {
        out.beginObject();
        out.name("data");
        out.beginArray();
        out.beginObject();
        out.name("id");
        out.value(value.getId());
        out.name("name");
        out.value(value.getName());
        out.endObject();
        out.endArray();
        out.endObject();
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     * @param in the stream to read from.
     * @return the converted Java object. May be null.
     */
    @Override
    public Type read(JsonReader in) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": {
         *     "id": 1,
         *     "name": "normal"
         *   }
         * }
         */
        in.beginObject();
        in.nextName();
        String message = in.nextString();

        if (message.equals("success")) {

            in.nextName();
            in.beginObject();
            in.nextName();
            int id = in.nextInt();
            in.nextName();
            String name = in.nextString();
            in.endObject();
            in.endObject();
            return new Type(id, name);
        } else {
            throw new IOException("Error while reading Type");
        }
    }
}
