package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class PokemonStatsFetcher {

    private Context ctx;

    public PokemonStatsFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public HashMap<Integer, List<PokemonStat>> fetchAndCache() {
        HashMap<Integer, List<PokemonStat>> statList = new HashMap<>();
        try {
            statList = (HashMap<Integer, List<PokemonStat>>) Cache.readCache(this.ctx, "data_pokemon_stats");
        } catch (Exception e) {
            try {
                statList = DataFetcher.fetchPokemonStats();
                Cache.writeCache(this.ctx, "data_pokemon_stats", statList);
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Pokemon stats list cannot be cached : " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        return statList;
    }
}
