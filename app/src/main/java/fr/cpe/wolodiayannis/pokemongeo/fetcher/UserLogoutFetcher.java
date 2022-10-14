package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class UserLogoutFetcher {
    /**
     * Context.
     */
    private Context ctx;

    /**
     * Constructor.
     *
     * @param ctx
     */
    public UserLogoutFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get user.
     *
     * @param user     user.
     */
    public void logoutAndClearCache(User user) {
        try {
            DataFetcher.logoutUser(user);
            // Clear all cache
            Cache.clearCache(this.ctx);
            logOnUiThread("[CACHE] Cache clear");
        } catch (Exception e) {
            logOnUiThreadError("[CACHE] Cache cannot be clear : " + e.getMessage());
        }
    }
}
