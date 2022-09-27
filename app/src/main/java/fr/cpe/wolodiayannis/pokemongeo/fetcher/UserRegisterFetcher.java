package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class UserRegisterFetcher {

    private Context ctx;

    public UserRegisterFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public void fetchAndCache(User user, String password) {
        try {
            DataFetcher.createUser(user, password);
            Cache.writeCache(this.ctx, "data_user", user);
            logOnUiThread("[CACHE] User cached");
        } catch (Exception e) {
            logOnUiThreadError("[CACHE] User cannot be cached : " + e.getMessage());
        }
    }
}
