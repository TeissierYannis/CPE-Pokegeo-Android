package fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.entity.lists.FriendList;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.FriendRequest;

public class FriendListAdapter extends TypeAdapter<FriendList> {

    @Override
    public void write(JsonWriter out, FriendList value) throws IOException {
         /*
        {
            "message": "success",
            "data": [
                {
                  "friend_id": 0,
                  "pseudo": "honor-ball",
                },
                {
                    "friend_id": 1,
                    "pseudo": "poke-ball",

                },
                ...
            ]
        }
        */

    }

    @Override
    public FriendList read(JsonReader in) throws IOException {
         /*
        {
            "message": "success",
            "data": [
                {
                  "friend_id": 0,
                  "pseudo": "honor-ball",
                },
                {
                    "friend_id": 1,
                    "pseudo": "poke-ball",

                },
                ...
            ]
        }
        */
        FriendList friendList = new FriendList();

        in.beginObject();
        in.nextName();
        String message = in.nextString();
        if (message.equals("success")) {
            in.nextName();
            in.beginArray();
            while (in.hasNext()) {
                in.beginObject();
                in.nextName();
                int friend_id = in.nextInt();
                in.nextName();
                String pseudo = in.nextString();
                in.endObject();
                friendList.addRequest(new FriendRequest(friend_id, pseudo));
            }
            in.endArray();
            in.endObject();
        }

        return friendList;
    }
}
