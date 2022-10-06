package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;

public class BasicResponseAdapter extends TypeAdapter<BasicResponse> {
    @Override
    public void write(JsonWriter out, BasicResponse value) throws IOException {
        out.beginObject();
        out.name("message").value(value.getMessage());
        out.endObject();
    }

    @Override
    public BasicResponse read(JsonReader in) throws IOException {
        /**
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
        String message = in.nextString();
        in.endObject();
        return new BasicResponse(message);
    }
}
