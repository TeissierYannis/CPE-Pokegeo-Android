package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class PokemonTypesFetcher {

    private Context ctx;

    public PokemonTypesFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public HashMap<Integer, List<Integer>> fetchAndCache() {
        HashMap<Integer, List<Integer>> typesList = new HashMap<>();
        try {
            typesList = (HashMap<Integer, List<Integer>>) Cache.readCache(this.ctx, "data_pokemon_types");
        } catch (Exception e) {
            try {
                typesList = DataFetcher.fetchPokemonTypes();
                Cache.writeCache(this.ctx, "data_pokemon_types", typesList);
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Pokemon types list cannot be cached : " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        return typesList;
    }
}
