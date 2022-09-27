package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class TypesFetcher {

    private Context ctx;

    public TypesFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public List<Type> fetchAndCache() {
        List<Type> typeList = new ArrayList<>();
        try {
            typeList = (List<Type>) Cache.readCache(this.ctx, "data_types");
            logOnUiThread("[CACHE] Type list loaded from cache");
        } catch (Exception e) {
            try {
                typeList = DataFetcher.fetchTypeList().getTypeList();
                Cache.writeCache(this.ctx, "data_types", typeList);
                logOnUiThread("[CACHE] Type list cached");
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Types list cannot be cached : " + exception.getMessage());
            }
        }
        return typeList;
    }
}
