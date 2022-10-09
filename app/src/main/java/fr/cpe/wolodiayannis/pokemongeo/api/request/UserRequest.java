package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.UserAPI;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.dto.UserDto;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.UserIsInit;
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
     */
    public static User checkUser(String email, String password) {
        Call<User> call = getAPI().getUser(new UserDto(email, password));
        try {
            User user = call.execute().body();
            if (user != null) {
                Datastore.getInstance().setUser(user);
            }
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
    public static User createUser(User user, String password) {
        Call<User> call = getAPI().createUser(new UserDto(user.getPseudo(), user.getEmail(), password));
        User userFromDB = null;
        try {
            userFromDB = call.execute().body();
            if (userFromDB != null) {
                Datastore.getInstance().setUser(userFromDB);
            }
            LogAPI("Post User");
        } catch (Exception e) {
            Logger.logOnUiThreadError(e.getMessage());
            e.printStackTrace();
            Datastore.getInstance().setUser(null);
        }
        return userFromDB;
    }

    /**
     * Update the user isInit
     * @param userId User ID.
     * @param isInit Is init.
     * @return Boolean
     */
    public static Boolean updateUserIsInit(int userId, boolean isInit) {
        UserIsInit userIsInit = new UserIsInit(userId, isInit);
        Call<UserIsInit> call = getAPI().updateUserIsInit(userIsInit);
        try {
            call.execute();

            Datastore.getInstance().getUser().setInit(isInit);

            LogAPI("Update User");
        } catch (Exception e) {
            Logger.logOnUiThreadError(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
