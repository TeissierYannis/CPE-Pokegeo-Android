package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;


public class CaughtPokemonListAdapter extends TypeAdapter<CaughtInventory> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to.
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
         *     },
         *     {
         *       "user_id": 1,
         *       "pokemon_id": 2
         *       "pokemon_experience": 0
         *       "current_life_state": 0
         *     }
         *     ...
         *   ]
         * }
         */

        out.beginObject();
        out.name("message").value("success");
        out.name("data");
        out.beginArray();

        for (int i = 0; i < value.getCaughtInventoryList().size(); i++) {
            out.beginObject();

            Pokemon pokemon = (Pokemon) value.getCaughtInventoryList().keySet().toArray()[i];

            out.name("user_id").value(Objects.requireNonNull(
                            value.getCaughtInventoryList().get(pokemon))
                    .getUserId());

            out.name("pokemon_id").value(Objects.requireNonNull(
                            value.getCaughtInventoryList().get(pokemon))
                    .getPokemonId());

            out.name("pokemon_experience").value(Objects.requireNonNull(
                            value.getCaughtInventoryList().get(pokemon))
                    .getPokemonExperience());

            out.name("current_life_state").value(Objects.requireNonNull(
                            value.getCaughtInventoryList().get(pokemon))
                    .getCurrentLifeState());

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

        HashMap<Pokemon, CaughtPokemon> caughtPokemonList = new HashMap<>();

        in.beginObject();
        in.nextName();
        String message = in.nextString();

        if (message.equals("success")) {

            in.nextName();
            in.beginArray();

            while (in.hasNext()) {
                in.beginObject();
                in.nextName();
                int userId = in.nextInt();
                in.nextName();
                int pokemonId = in.nextInt();
                in.nextName();
                int pokemonExperience = in.nextInt();
                in.nextName();
                int currentLifeState = in.nextInt();
                in.nextName();
                String caughtTime = in.nextString();
                in.endObject();

                // format caughtTime to convert it to a Timestamp
                caughtTime = caughtTime.replace("T", " ");
                caughtTime = caughtTime.replace("Z", "");

                // create empty Pokemon
                Pokemon pokemon = new Pokemon();
                Timestamp timestamp = Timestamp.valueOf(caughtTime);
                caughtPokemonList.put(pokemon, new CaughtPokemon(userId, pokemonId, pokemonExperience, currentLifeState, timestamp));
            }
            in.endArray();
            in.endObject();
            if (caughtPokemonList.size() > 0) {
                return new CaughtInventory(caughtPokemonList);
            } else {
                // empty inventory
                return new CaughtInventory();
            }
        } else {
            throw new IOException("Failed to fetch caught pokemon list");
        }
    }
}
