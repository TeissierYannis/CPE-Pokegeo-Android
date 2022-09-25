package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.UserAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import retrofit2.Call;

public class UserRequest extends BaseRequest {

    /**
     * Get UserAPI.
     * @return UserAPI.
     */
    protected static UserAPI getAPI() {
        return getRetrofit().create(UserAPI.class);
    }

    /**
     * Get the user.
     * @param id User id.
     * @return User.
     */
    public static User getUserFromID(int id) {
        Call<User> call = getAPI().getUser(id);
        try {
            User user = call.execute().body();
            LogAPI("User");
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create the user.
     * @param user User.
     */
    public static void createUser(User user) {
        Call<User> call = getAPI().createUser(user);
        try {
            // TODO post ?
            LogAPI("Post User");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
