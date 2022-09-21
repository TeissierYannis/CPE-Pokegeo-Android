package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.TypeList;

public class TypeListAdapter extends TypeAdapter<TypeList> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, TypeList value) throws IOException {
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
        for (int i = 0; i < value.getTypeList().size(); i++) {
            out.beginObject();
            out.name("id").value(value.getTypeList().get(i).getId());
            out.name("name").value(value.getTypeList().get(i).getName());
            out.endObject();
        }
        out.endArray();
        out.endObject();
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @param in
     * @return the converted Java object. May be null.
     */
    @Override
    public TypeList read(JsonReader in) throws IOException {
        /**
         * {
         *  "message": "ok",
         *  "data": [
         *    {
         *    "id": 1,
         *    "name": "Potion",
         *    },
         *    {
         *    "id": 2,
         *    "name": "Super Potion",
         *    }
         *    ]
         *    }
         */
        List<Type> typeList = new ArrayList<>();
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
            typeList.add(new Type(id, name));
        }
        in.endArray();
        in.endObject();
        return new TypeList(typeList);
    }
}
