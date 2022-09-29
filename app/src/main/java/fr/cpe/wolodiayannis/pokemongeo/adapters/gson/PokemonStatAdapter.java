package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;

public class PokemonStatAdapter extends TypeAdapter<PokemonStat> {
    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(com.google.gson.stream.JsonWriter out, PokemonStat value) throws IOException {
        /*
         * {
         *     "message": "success",
         *     "data":
         *          {
         *              "stat_id": 1,
         *              "name": "name",
         *              "base_stat": 49
         *          }
         * }
         */

        out.beginObject();
        out.name("data");
        out.beginObject();
        out.name("stat");
        out.value(value.getBaseStat());
        out.name("base_stat");
        out.value(value.getBaseStat());
        out.endObject();
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
    public PokemonStat read(JsonReader in) throws IOException {
        /*
         * {
         *     "message": "success",
         *     "data": [
         *          {
         *              "stat_id": 1,
         *              "name": "name",
         *              "base_stat": 49
         *          }
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
            in.nextName();
            int baseStat = in.nextInt();
            in.endObject();
            in.endObject();

            Stat stat = new Stat(id, name);
            return new PokemonStat(stat, baseStat);
        } else {
            throw new IOException("Error while reading PokemonStat");
        }
    }
}
