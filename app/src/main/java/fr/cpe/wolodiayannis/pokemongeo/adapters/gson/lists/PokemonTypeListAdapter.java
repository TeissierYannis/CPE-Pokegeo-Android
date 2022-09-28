package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonAbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonTypeList;

public class PokemonTypeListAdapter extends TypeAdapter<PokemonTypeList> {
    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out  the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, PokemonTypeList value) throws IOException {
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
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @param in the stream to read from
     * @return the converted Java object. May be null.
     */
    @Override
    public PokemonTypeList read(JsonReader in) throws IOException {
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
        HashMap<Integer, List<Integer>> typeList = new HashMap<>();
        in.beginObject();
        in.nextName();
        in.nextString();
        in.nextName();
        in.beginArray();
        while (in.hasNext()) {
            // If we have the same pokemon id, we add the ability id to the list
            // If we have a new pokemon id, we create a new list and add the ability id to the list
            in.beginObject();
            in.nextName();
            int pokemonID = in.nextInt();
            in.nextName();
            int typeID = in.nextInt();
            in.endObject();
            if (!typeList.containsKey(pokemonID)) {
                typeList.put(pokemonID, new ArrayList<>());
            }
            Objects.requireNonNull(typeList.get(pokemonID)).add(typeID);
        }
        in.endArray();
        in.endObject();
        System.out.println(in.peek());
        return new PokemonTypeList(typeList);
    }
}
