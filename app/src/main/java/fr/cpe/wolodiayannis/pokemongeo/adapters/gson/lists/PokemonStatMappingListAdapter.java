package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonStatMappingList;

public class PokemonStatMappingListAdapter extends TypeAdapter<PokemonStatMappingList> {
    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(com.google.gson.stream.JsonWriter out, PokemonStatMappingList value) throws java.io.IOException {
        /*
         * {
         *   "message": "success",
         *   "data": [
         *     {
         *         "pokemon_id": 0,
         *         "stat_id": 1,
         *         "name": "hp",
         *         "base_stat": 33
         *     },
         *     {
         *        "pokemon_id": 0,
         *        "stat_id": 2,
         *        "name": "attack",
         *        "base_stat": 136
         *      },
         *    ...
         *    ]
         *  }
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
    public PokemonStatMappingList read(com.google.gson.stream.JsonReader in) throws java.io.IOException {
        /*
         * {
         *   "message": "success",
         *   "data": [
         *     {
         *         "pokemon_id": 0,
         *         "stat_id": 1,
         *         "name": "hp",
         *         "base_stat": 33
         *     },
         *     {
         *        "pokemon_id": 0,
         *        "stat_id": 2,
         *        "name": "attack",
         *        "base_stat": 136
         *      },
         *    ...
         *    ]
         *  }
         */
        HashMap<Integer, List<PokemonStat>> statsList = new HashMap<>();
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
                int statID = in.nextInt();
                in.nextName();
                String statName = in.nextString();
                in.nextName();
                int base_stat = in.nextInt();
                in.endObject();

                if (!statsList.containsKey(pokemonID)) {
                    statsList.put(pokemonID, new ArrayList<>());
                }
                Objects.requireNonNull(statsList.get(pokemonID)).add(new PokemonStat(new Stat(statID, statName), base_stat));
            }
            in.endArray();
            in.endObject();
            System.out.println(in.peek());
            return new PokemonStatMappingList(statsList);
        } else {
            throw new IOException("Cannot fetch Pokemon stat list");
        }
    }
}
