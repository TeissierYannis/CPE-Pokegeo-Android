package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class UserUpdateFetcher {

    private Context ctx;

    public UserUpdateFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public void fetchAndCache(User user, boolean isInit) {
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
}
