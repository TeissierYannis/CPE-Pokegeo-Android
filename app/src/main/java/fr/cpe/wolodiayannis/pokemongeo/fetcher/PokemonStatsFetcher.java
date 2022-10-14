package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class PokemonStatsFetcher {

    /**
     * Context.
     */
    private final Context ctx;

    /**
     * Constructor.
     *
     * @param ctx context.
     */
    public PokemonStatsFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get pokemon stats.
     *
     * @return pokemon stats.
     */
    public HashMap<Integer, List<PokemonStat>> fetchAndCache() {
        HashMap<Integer, List<PokemonStat>> statList = new HashMap<>();
        try {
            statList = (HashMap<Integer, List<PokemonStat>>) Cache.readCache(this.ctx, "data_pokemon_stats");
        } catch (Exception e) {
            try {
                statList = DataFetcher.fetchPokemonStats();
                Cache.writeCache(this.ctx, "data_pokemon_stats", statList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return statList;
    }
}
