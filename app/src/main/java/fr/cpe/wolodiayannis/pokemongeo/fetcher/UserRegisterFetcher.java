package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;


import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class UserRegisterFetcher {

    /**
     * Context.
     */
    private final Context ctx;

    /**
     * Constructor.
     *
     * @param ctx context.
     */
    public UserRegisterFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get user.
     *
     * @param user     user.
     * @param password password.
     */
    public void fetchAndCache(User user, String password) {
        try {
            User userFromDB = DataFetcher.createUser(user, password);
            if (null != userFromDB) {
                Cache.writeCache(this.ctx, "data_user", userFromDB);
            }
            logOnUiThread("[CACHE] User cached");
        } catch (Exception e) {
            logOnUiThreadError("[CACHE] User cannot be cached : " + e.getMessage());
        }
    }
}
