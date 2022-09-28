package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class StatsFetcher {

    private Context ctx;

    public StatsFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public List<Stat> fetchAndCache() {
        List<Stat> statList = new ArrayList<>();
        try {
            statList = (List<Stat>) Cache.readCache(this.ctx, "data_stats");
            logOnUiThread("[CACHE] Stat list loaded from cache");
        } catch (Exception e) {
            try {
                statList = DataFetcher.fetchStatList().getStatsList();
                Cache.writeCache(this.ctx, "data_stats", statList);
                logOnUiThread("[CACHE] Stat list cached");
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Stat list cannot be cached : " + exception.getMessage());
            }
        }
        return statList;
    }
}
