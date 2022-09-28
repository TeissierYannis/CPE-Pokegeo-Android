package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.StatList;

public class StatListAdapter extends TypeAdapter<StatList> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out the stream to write to.
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, StatList value) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": [
         *     {
         *       "id": 1,
         *       "name": "hp"
         *     },
         *     {
         *       "id": 2,
         *       "name": "attack"
         *     }
         *     ...
         *   ]
         * }
         */
        out.beginObject();
        out.name("message").value("success");
        out.name("data");
        out.beginArray();
        for (int i = 0; i < value.getStatsList().size(); i++) {
            out.beginObject();
            out.name("id").value(value.getStatsList().get(i).getId());
            out.name("name").value(value.getStatsList().get(i).getName());
            out.endObject();
        }
        out.endArray();
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
    public StatList read(JsonReader in) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": [
         *     {
         *       "id": 1,
         *       "name": "hp"
         *     },
         *     {
         *       "id": 2,
         *       "name": "attack"
         *     }
         *     ...
         *   ]
         * }
         */
        List<Stat> statList = new ArrayList<>();
        in.beginObject();
        in.nextName();
        in.nextString();
        in.nextName();
        in.beginArray();
        while (in.hasNext()) {
            in.beginObject();
            in.nextName();
            int id = in.nextInt();
            in.nextName();
            String name = in.nextString();
            in.endObject();
            statList.add(new Stat(id, name));
        }
        in.endArray();
        in.endObject();
        return new StatList(statList);
    }
}
