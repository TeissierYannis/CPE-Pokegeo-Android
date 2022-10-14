package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;
import fr.cpe.wolodiayannis.pokemongeo.exception.CacheException;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class UserLoginFetcher {

    /**
     *
     */
    private final Context ctx;

    /**
     * Constructor.
     *
     * @param ctx context.
     */
    public UserLoginFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get user.
     *
     * @param pseudo   pseudo.
     * @param password password.
     */
    public void fetchAndCache(String pseudo, String password) {
        Datastore datastore = Datastore.getInstance();
        // Check if user is already cached
        try {
            User user = (User) Cache.readCache(this.ctx, "data_user");
            if (user == null || user.getId() == 0) {
                throw new CacheException("User not found in cache");
            }
            datastore.setUser(user);
            logOnUiThread("[CACHE] User loaded from cache");
        } catch (CacheException e) {
            // If not, fetch it from the server
            try {
                User user = DataFetcher.checkUser(pseudo, password);
                datastore.setUser(user);
                Cache.writeCache(this.ctx, "data_user", user);
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] User cannot be cached : " + exception.getMessage());
                exception.printStackTrace();
            }
        }
    }
}
