package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.exception.CacheException;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class CaughtInventoryFetcher {

    private Context ctx;

    public CaughtInventoryFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public CaughtInventory fetch(int userID) {
        CaughtInventory caughtPokemonList = null;
        try {
            caughtPokemonList = (CaughtInventory) Cache.readCache(this.ctx, "data_caught_pokemon");
            // if list is null, fetch it
            if (caughtPokemonList == null) {
                throw new Exception("CaughtInventory is null or empty");
            }
        } catch (Exception e) {
            try {
                caughtPokemonList = DataFetcher.fetchCaughtPokemonList(userID);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        Datastore.getInstance().setCaughtInventory(caughtPokemonList);
        return caughtPokemonList;
    }

    /**
     * Cache the caught pokemon list/
     * @param inventory the caught pokemon list

     * @throws CacheException
     */
    public void cacheInventory(CaughtInventory inventory) throws CacheException {
        if (inventory != null) {
            Cache.writeCache(this.ctx, "data_caught_pokemon", inventory);
        }
    }

    public void addPokemonAndCache(CaughtPokemon caughtPokemon) {
        try {
            if (caughtPokemon != null) {
                DataFetcher.addCaughtPokemon(caughtPokemon);
                Cache.writeCache(this.ctx, "data_caught_pokemon", Datastore.getInstance().getCaughtInventory());
            }
            logOnUiThread("[CACHE] CaughtInventory cached");
        } catch (Exception e) {
            logOnUiThreadError("[CACHE] CaughtInventory cannot be cached : " + e.getMessage());
        }
    }

    public void updatePokemonAndCache(CaughtPokemon caughtPokemon) {
        try {
            if (caughtPokemon != null) {
                DataFetcher.updateCaughtPokemon(caughtPokemon);
                Cache.writeCache(this.ctx, "data_caught_pokemon", Datastore.getInstance().getCaughtInventory());
            }
            logOnUiThread("[CACHE] CaughtInventory cached");
        } catch (Exception e) {
            logOnUiThreadError("[CACHE] CaughtInventory cannot be cached : " + e.getMessage());
        }
    }
}
