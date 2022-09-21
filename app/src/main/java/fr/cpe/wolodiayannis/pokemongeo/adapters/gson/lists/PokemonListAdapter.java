package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;

public class PokemonListAdapter extends TypeAdapter<PokemonList> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, PokemonList value) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": [
         * {
         *     "id": 1,
         *     "name": "bulbasaur",
         *     "height": 7,
         *     "weight": 69,
         *     "description": "A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokémon.",
         *     "generation_id": 1,
         *     "evolution_chain_id": 1
         *   },
         * {
         *     "id": 1,
         *     "name": "bulbasaur",
         *     "height": 7,
         *     "weight": 69,
         *     "description": "A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokémon.",
         *     "generation_id": 1,
         *     "evolution_chain_id": 1
         *   }
         * ]
         * }
         */
        out.beginObject();
        out.name("message").value("success");
        out.name("data");
        out.beginArray();
        for (int i = 0; i < value.getPokemonList().size(); i++) {
            out.beginObject();
            out.name("id").value(value.getPokemonList().get(i).getId());
            out.name("name").value(value.getPokemonList().get(i).getName());
            out.name("height").value(value.getPokemonList().get(i).getName());
            out.name("weight").value(value.getPokemonList().get(i).getName());
            out.name("description").value(value.getPokemonList().get(i).getName());
            out.name("generation_id").value(value.getPokemonList().get(i).getName());
            out.name("evolution_chain_id").value(value.getPokemonList().get(i).getName());
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
    public PokemonList read(JsonReader in) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": [
         * {
         *     "id": 1,
         *     "name": "bulbasaur",
         *     "height": 7,
         *     "weight": 69,
         *     "description": "A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokémon.",
         *     "generation_id": 1,
         *     "evolution_chain_id": 1
         *   },
         * {
         *     "id": 1,
         *     "name": "bulbasaur",
         *     "height": 7,
         *     "weight": 69,
         *     "description": "A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokémon.",
         *     "generation_id": 1,
         *     "evolution_chain_id": 1
         *   }
         * ]
         * }
         */
        List<Pokemon> pokemonList = new ArrayList<>();
        in.beginObject();
        in.nextName();
        in.nextString();
        in.nextName();
        in.beginArray();
        int evolutionChainID = 0;
        while (in.hasNext()) {
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
            try {
                evolutionChainID = in.nextInt();
            } catch (IllegalStateException e) {
                evolutionChainID = 0;
                in.nextNull();
            }
            in.endObject();
            pokemonList.add(new Pokemon(
                    id,
                    name,
                    height,
                    weight,
                    description,
                    generationID,
                    evolutionChainID
            ));
        }
        in.endArray();
        in.endObject();
        return new PokemonList(pokemonList);
    }
}
