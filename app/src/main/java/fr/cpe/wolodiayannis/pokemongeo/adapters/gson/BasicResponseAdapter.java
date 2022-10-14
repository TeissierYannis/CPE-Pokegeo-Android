package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;

public class BasicResponseAdapter extends TypeAdapter<BasicResponse> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out   the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, BasicResponse value) throws IOException {
        out.beginObject();
        out.name("message").value(value.getMessage());
        out.endObject();
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @param in the stream to read from
     * @return the converted Java object. May be null.
     */
    @Override
    public BasicResponse read(JsonReader in) throws IOException {
        /*
         {
          message: "success",
              data: {
                user_id: 1,
                pokemon_id: 1,
                caught_time: "2018-05-01 00:00:00",
                pokemon_experience: 0,
                current_life_state: 0
              }
          }
        */
        in.beginObject();
        in.nextName();
        String message = in.nextString();
        in.endObject();
        return new BasicResponse(message);
    }
}
