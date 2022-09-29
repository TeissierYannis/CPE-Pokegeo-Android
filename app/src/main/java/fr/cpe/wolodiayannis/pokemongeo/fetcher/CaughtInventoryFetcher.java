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
        CaughtInventory caughtPokemonList = null;
        try {
            caughtPokemonList = (CaughtInventory) Cache.readCache(this.ctx, "data_caught_pokemon");
            // if list is null, fetch it
            if (caughtPokemonList == null || caughtPokemonList.getcaughtInventoryList().size() == 0) {
                throw new Exception("CaughtInventory is null or empty");
            }
            logOnUiThread("[CACHE] Caught pokemon list loaded from cache");
        } catch (Exception e) {
            try {
                caughtPokemonList = DataFetcher.fetchCaughtPokemonList(userID);
                if (caughtPokemonList != null) {
                    Cache.writeCache(this.ctx, "data_caught_pokemon", caughtPokemonList);
                    logOnUiThread("[CACHE] Caught pokemon list saved to cache");
                }
                throw new Exception("CaughtInventory is null or empty");
            } catch (Exception exception) {
                logOnUiThreadError("[CACHE] Caught pokemon list cannot be cached : " + exception.getMessage());
            }
        }
        return caughtPokemonList;
    }
}
