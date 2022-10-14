package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class TypesFetcher {

    /**
     * Context.
     */
    private final Context ctx;

    /**
     * Constructor.
     *
     * @param ctx context.
     */
    public TypesFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get types.
     *
     * @return types.
     */
    public List<Type> fetchAndCache() {
        List<Type> typeList = new ArrayList<>();
        try {
            typeList = (List<Type>) Cache.readCache(this.ctx, "data_types");
        } catch (Exception e) {
            try {
                typeList = DataFetcher.fetchTypeList().getTypeList();
                Cache.writeCache(this.ctx, "data_types", typeList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return typeList;
    }
}
