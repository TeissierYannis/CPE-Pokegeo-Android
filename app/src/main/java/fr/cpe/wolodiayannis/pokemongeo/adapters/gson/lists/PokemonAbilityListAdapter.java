package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;;import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonAbilityList;

public class PokemonAbilityListAdapter extends TypeAdapter<PokemonAbilityList> {
    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, PokemonAbilityList value) throws IOException {
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
    public PokemonAbilityList read(JsonReader in) throws IOException {
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
        HashMap<Integer, List<Integer>> abilityList = new HashMap<>();
        in.beginObject();
        in.nextName();
        String message = in.nextString();
        if (message.equals("success")) {

            in.nextName();
            in.beginArray();
            while (in.hasNext()) {
                // If we have the same pokemon id, we add the ability id to the list
                // If we have a new pokemon id, we create a new list and add the ability id to the list
                in.beginObject();
                in.nextName();
                int pokemonID = in.nextInt();
                in.nextName();
                int abilityID = in.nextInt();
                in.endObject();
                if (!abilityList.containsKey(pokemonID)) {
                    abilityList.put(pokemonID, new ArrayList<>());
                }
                Objects.requireNonNull(abilityList.get(pokemonID)).add(abilityID);
            }
            in.endArray();
            in.endObject();
            System.out.println(in.peek());
            return new PokemonAbilityList(abilityList);
        } else {
            throw new IOException("Cannot fetch ability list");
        }
    }
}
