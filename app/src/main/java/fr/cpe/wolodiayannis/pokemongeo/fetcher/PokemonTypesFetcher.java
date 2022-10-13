package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class PokemonTypesFetcher {

    /**
     * Context.
     */
    private Context ctx;

    /**
     * Constructor.
     * @param ctx context.
     */
    public PokemonTypesFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get pokemon types.
     * @return pokemon types.
     */
    public HashMap<Integer, List<Integer>> fetchAndCache() {
        HashMap<Integer, List<Integer>> typesList = new HashMap<>();
        try {
            typesList = (HashMap<Integer, List<Integer>>) Cache.readCache(this.ctx, "data_pokemon_types");
        } catch (Exception e) {
            try {
                typesList = DataFetcher.fetchPokemonTypes();
                Cache.writeCache(this.ctx, "data_pokemon_types", typesList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return typesList;
    }
}
