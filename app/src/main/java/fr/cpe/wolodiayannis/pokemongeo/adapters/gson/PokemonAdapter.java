package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

public class PokemonAdapter extends TypeAdapter<Pokemon> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(com.google.gson.stream.JsonWriter out, Pokemon value) throws IOException {
        out.beginObject();
        out.name("data");
        out.beginObject();
        out.name("id");
        out.value(value.getId());
        out.name("name");
        out.value(value.getName());
        out.name("height");
        out.value(value.getHeight());
        out.name("weight");
        out.value(value.getWeight());
        out.name("description");
        out.value(value.getDescription());
        out.name("generation_id");
        out.value(value.getGenerationId());
        out.name("evolution_chain_id");
        out.value(value.getEvolutionChainId());
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
    public Pokemon read(JsonReader in) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": {
         *     "id": 1,
         *     "name": "bulbasaur",
         *     "height": 7,
         *     "weight": 69,
         *     "description": "A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokémon.",
         *     "generation_id": 1,
         *     "evolution_chain_id": 1
         *   }
         * }
         */
        System.out.println("[DEBUG] " + in);
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
            int height = in.nextInt();
            in.nextName();
            int weight = in.nextInt();
            in.nextName();
            String description = in.nextString();
            in.nextName();
            int generationID = in.nextInt();
            in.nextName();
            int evolutionChainID = in.nextInt();
            in.endObject();
            in.endObject();

            return new Pokemon(id, name, height, weight, description, generationID, evolutionChainID);
        } else {
            throw new IOException("Error while reading Pokemon");
        }
    }
}
