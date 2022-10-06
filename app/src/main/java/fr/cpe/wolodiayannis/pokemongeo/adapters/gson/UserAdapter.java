package fr.cpe.wolodiayannis.pokemongeo.adapters.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.Timestamp;

import fr.cpe.wolodiayannis.pokemongeo.entity.User;

public class UserAdapter extends TypeAdapter<User> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out  the stream to write to
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, User value) throws IOException {

        out.beginObject();
        out.name("data");
        out.beginArray();
        out.beginObject();
        out.name("id");
        out.value(value.getId());
        out.name("pseudo");
        out.value(value.getPseudo());
        out.name("email");
        out.value(value.getEmail());
        out.name("experience");
        out.value(value.getExperience());
        out.name("is_init");
        out.value(value.isInit());
        out.name("created_at");
        out.value(value.getCreatedAt().toString());
        out.name("token");
        out.value(value.getJwt());
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
    "message": "success",
    "data": {
        "user": {
            "id": 1,
            "pseudo": "yannis",
            "email": "yannis@cpe.fr",
            "experience": 0,
            "is_init": 0,
            "created_at": "2022-09-25T12:13:06.000Z"
        },
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwicHNldWRvIjoieWFubmlzIiwiaWF0IjoxNjY0MTA3OTg2LCJleHAiOjE2OTU2NDM5ODZ9.Y9haAoSYRpxXF-KIU5W5ebijvGVsUZq3ygT-v_WbBvs"
    }
}
         */

        in.beginObject();
        in.nextName();
        String message = in.nextString();

        if (message.equals("success")) {

            in.nextName();
            in.beginObject();
            in.nextName();
            in.beginObject();
            in.nextName();
            int id = in.nextInt();
            in.nextName();
            String pseudo = in.nextString();
            in.nextName();
            String email = in.nextString();
            in.nextName();
            int experience = in.nextInt();
            in.nextName();
            int isInit = in.nextInt();
            in.nextName();
            String createdAtString = in.nextString();
            in.endObject();
            in.nextName();
            String token = in.nextString();
            in.endObject();
            in.endObject();
            // String to Timestamp
            boolean isInitBool = isInit == 1;

            // from 2022-09-25T12:13:06.000Z to timestamp
            Timestamp createdAt = Timestamp.valueOf(createdAtString.replace("Z", "").replace("T", " "));

            return new User(id, pseudo, email, experience, isInitBool, createdAt, token);
        } else {
            throw new IOException("SQL Error");
        }
    }
}
