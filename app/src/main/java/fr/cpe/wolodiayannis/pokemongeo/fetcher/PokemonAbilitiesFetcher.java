package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class PokemonAbilitiesFetcher {

    private Context ctx;

    public PokemonAbilitiesFetcher(Context ctx) {
        this.ctx = ctx;
    }

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
