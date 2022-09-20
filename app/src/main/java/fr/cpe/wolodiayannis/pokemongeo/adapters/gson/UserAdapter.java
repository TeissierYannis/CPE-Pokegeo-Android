package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.Timestamp;

import fr.cpe.wolodiayannis.pokemongeo.entity.User;

public class UserAdapter extends TypeAdapter<User>{

    @Override
    public void write(JsonWriter out, User value) throws IOException {

        out.beginObject();
        out.name("data");
        out.beginArray();
        out.beginObject();
        out.name("id");
        out.value(value.getId());
        out.name("name");
        out.value(value.getName());
        out.name("pseudo");
        out.value(value.getPseudo());
        out.name("email");
        out.value(value.getEmail());
        out.name("experience");
        out.value(value.getExperience());
        out.name("is_init");
        out.value(value.getIsInit());
        out.name("created_at");
        out.value(value.getCreatedAt().toString());
        out.endObject();
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
    public User read(JsonReader in) throws IOException {
        /*
         * {
         *   "message": "success",
         *   "data": {
         *     "id": 1,
         *     "name": "default"
         *     "pseudo": "default"
         *     "email": "default@pokegeo.com"
         *     "experience": 0
         *     "is_init": 0
         *     "created_at": "2022-01-01 00:00:00"
         *   }
         * }
         */

        in.beginObject();
        in.nextName();
        in.nextString();
        in.nextName();
        in.beginObject();
        in.nextName();
        int id = in.nextInt();
        in.nextName();
        String name = in.nextString();
        in.nextName();
        String pseudo = in.nextString();
        in.nextName();
        String email = in.nextString();
        in.nextName();
        int experience = in.nextInt();
        in.nextName();
        boolean isInit = in.nextBoolean();
        in.nextName();
        String sCreatedAt = in.nextString();
        in.endObject();
        in.endObject();

        // String to Timestamp
        Timestamp createdAt = Timestamp.valueOf(sCreatedAt);

        return new User(id, name, pseudo, email, experience, isInit, createdAt);
    }
}
