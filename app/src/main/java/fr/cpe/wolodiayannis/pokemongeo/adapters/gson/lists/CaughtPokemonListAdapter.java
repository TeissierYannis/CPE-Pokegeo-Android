package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;


public class CaughtPokemonListAdapter extends TypeAdapter<CaughtInventory> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out the stream to write to.
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, CaughtInventory value) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": [
         *     {
         *       "user_id": 1,
         *       "pokemon_id": 1
         *       "pokemon_experience": 0
         *       "current_life_state": 0
         *       "caught_time": 1664355450
         *     },
         *     {
         *       "user_id": 1,
         *       "pokemon_id": 2
         *       "pokemon_experience": 0
         *       "current_life_state": 0
         *       "caught_time": 1664355450
         *     }
         *     ...
         *   ]
         * }
         */

        // TODO
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @param in the stream to read from.
     * @return the converted Java object. May be null.
     */
    @Override
    public CaughtInventory read(JsonReader in) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": [
         *     {
         *       "user_id": 1,
         *       "pokemon_id": 1
         *       "pokemon_experience": 0
         *       "current_life_state": 0
         *       "caught_time": 1664355450
         *     },
         *     {
         *       "user_id": 1,
         *       "pokemon_id": 2
         *       "pokemon_experience": 0
         *       "current_life_state": 0
         *       "caught_time": 1664355450
         *     }
         *     ...
         *   ]
         * }
         */

        // TODO

        return null;
    }
}
