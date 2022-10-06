package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;

public class AbilityListAdapter extends TypeAdapter<AbilityList> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, AbilityList value) throws IOException {
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
        for (int i = 0; i < value.getAbilityList().size(); i++) {
            out.beginObject();
            out.name("id").value(value.getAbilityList().get(i).getId());
            out.name("name").value(value.getAbilityList().get(i).getName());
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
    public AbilityList read(JsonReader in) throws IOException {
        /*
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
        List<Ability> abilityList = new ArrayList<>();
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
                in.endObject();
                abilityList.add(new Ability(id, name));
            }
            in.endArray();
            in.endObject();
            System.out.println(in.peek());
            return new AbilityList(abilityList);
        } else {
            throw new IOException("Cannot fetch ability list");
        }
    }
}
