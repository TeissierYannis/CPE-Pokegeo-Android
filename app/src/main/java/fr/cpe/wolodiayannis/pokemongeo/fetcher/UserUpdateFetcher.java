package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class UserUpdateFetcher {

    /**
     * Context.
     */
    private Context ctx;

    /**
     * Constructor.
     * @param ctx context.
     */
    public UserUpdateFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get user.
     * @param user user.
     * @param isInit is init.
     */
    public void updateAndCacheInit(User user, boolean isInit) {
        Datastore datastore = Datastore.getInstance();
        try {
            DataFetcher.updateUserIsInit(user.getId(), isInit);
            datastore.getUser().setInit(isInit);
            Cache.writeCache(this.ctx, "data_user", user);
        } catch (Exception exception) {
            logOnUiThreadError("[CACHE] User cannot be cached : " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    public void updateAndCacheMoneyAndExp(User user, int money, int exp) {
        Datastore datastore = Datastore.getInstance();
        try {
            DataFetcher.updateUserMoneyAndExp(user.getId(), money, exp);
            datastore.getUser().setMoney(money);
            datastore.getUser().setExperience(exp);
            Cache.writeCache(this.ctx, "data_user", user);
        } catch (Exception exception) {
            logOnUiThreadError("[CACHE] User cannot be cached : " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
