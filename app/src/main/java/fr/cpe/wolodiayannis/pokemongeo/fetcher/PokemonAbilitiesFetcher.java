package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class PokemonAbilitiesFetcher {

    /**
     * Context.
     */
    private Context ctx;

    /**
     * Constructor.
     * @param ctx context.
     */
    public PokemonAbilitiesFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get pokemon abilities.
     * @return pokemon abilities.
     */
    public HashMap<Integer, List<Integer>> fetchAndCache() {
        HashMap<Integer, List<Integer>> abilityList = new HashMap<>();
        try {
            abilityList = (HashMap<Integer, List<Integer>>) Cache.readCache(this.ctx, "data_pokemon_abilities");
        } catch (Exception e) {
            try {
                abilityList = DataFetcher.fetchPokemonAbilities();
                Cache.writeCache(this.ctx, "data_pokemon_abilities", abilityList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return abilityList;
    }
}
