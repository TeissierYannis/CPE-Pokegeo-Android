package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.Timestamp;

import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;

public class CaughtPokemonAdapter extends TypeAdapter<CaughtPokemon> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to.
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, CaughtPokemon value) throws IOException {
        out.beginObject();
        out.name("userID").value(value.getUserId());
        out.name("pokemonID").value(value.getPokemonId());
        out.name("pokemonExperience").value(value.getPokemonExperience());
        out.name("currentLifeState").value(value.getCurrentLifeState());
        out.endObject();
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @param in the stream to read from.
     * @return the converted Java object. May be null.
     * @throws IOException if the next value cannot be read.
     */
    @Override
    public CaughtPokemon read(JsonReader in) throws IOException {
        /*
         * {
         *  message: "success",
         *  data: {
         *    user_id: 1,
         *    pokemon_id: 1,
         *    caught_time: "2018-05-01 00:00:00",
         *    pokemon_experience: 0,
         *    current_life_state: 0
         *    }
         *    }
         *
         */
        in.beginObject();
        in.nextName();
        int user_id = in.nextInt();
        in.nextName();
        int pokemon_id = in.nextInt();
        in.nextName();
        String caught_time = in.nextString();
        in.nextName();
        int pokemon_experience = in.nextInt();
        in.nextName();
        int current_life_state = in.nextInt();
        in.endObject();
        return new CaughtPokemon(user_id, pokemon_id, pokemon_experience, current_life_state, Timestamp.valueOf(caught_time));
    }
}
