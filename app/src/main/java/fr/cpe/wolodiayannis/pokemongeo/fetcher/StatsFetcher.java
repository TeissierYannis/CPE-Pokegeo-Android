package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class StatsFetcher {

    /**
     * Context.
     */
    private final Context ctx;

    /**
     * Constructor.
     *
     * @param ctx context.
     */
    public StatsFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get stats.
     *
     * @return stats.
     */
    public List<Stat> fetchAndCache() {
        List<Stat> statList = new ArrayList<>();
        try {
            statList = (List<Stat>) Cache.readCache(this.ctx, "data_stats");
        } catch (Exception e) {
            try {
                statList = DataFetcher.fetchStatList().getStatsList();
                Cache.writeCache(this.ctx, "data_stats", statList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return statList;
    }
}
