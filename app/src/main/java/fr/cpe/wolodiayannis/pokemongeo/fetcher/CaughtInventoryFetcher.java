package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class CaughtInventoryFetcher {

    private Context ctx;

    public CaughtInventoryFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public CaughtInventory fetchAndCache(int userID) {
        CaughtInventory caughtPokemonList = new CaughtInventory();
        try {
            caughtPokemonList = (CaughtInventory) Cache.readCache(this.ctx, "data_caught_pokemon");
            logOnUiThread("[CACHE] Caught pokemon list loaded from cache");
        } catch (Exception e) {
            try {
                caughtPokemonList = DataFetcher.fetchCaughtPokemonList(userID);
                Cache.writeCache(this.ctx, "data_caught_pokemon", caughtPokemonList);
                logOnUiThread("[CACHE] Caught pokemon list cached");
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Caught pokemon list cannot be cached : " + exception.getMessage());
            }
        }
        return caughtPokemonList;
    }
}
