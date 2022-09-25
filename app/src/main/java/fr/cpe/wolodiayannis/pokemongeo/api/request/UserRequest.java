package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.UserAPI;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.dto.UserDto;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.utils.Logger;
import retrofit2.Call;

public class UserRequest extends BaseRequest {

    /**
     * Get UserAPI.
     *
     * @return UserAPI.
     */
    protected static UserAPI getAPI() {
        return getRetrofit().create(UserAPI.class);
    }

    /**
     * Get the user.
     *
     * @param email    User email.
     * @param password User password.
     * @return User.
     */
    public static User checkUser(String email, String password) {
        Call<User> call = getAPI().getUser(email, password);
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
     *
     * @param user User.
     */
    public static void createUser(User user, String password) {
        Call<User> call = getAPI().createUser(new UserDto(user.getPseudo(), user.getEmail(), password));
        try {
            User userFromDB = call.execute().body();
            Datastore.getInstance().setUser(userFromDB);
            LogAPI("Post User");
        } catch (Exception e) {
            Logger.logOnUiThreadError(e.getMessage());
            e.printStackTrace();
            Datastore.getInstance().setUser(null);
        }
    }
}
