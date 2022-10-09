package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;
import fr.cpe.wolodiayannis.pokemongeo.exception.CacheException;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class ItemInventoryFetcher {

    private Context ctx;

    /**
     * Constructor.
     * @param ctx the context
     */
    public ItemInventoryFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Fetch the item inventory.
     * @param userID the user id
     * @return the item inventory
     */
    public ItemInventory fetch(int userID) {
        ItemInventory itemInventory = null;
        try {
            itemInventory = (ItemInventory) Cache.readCache(this.ctx, "data_item_inventory");
            // if list is null, fetch it
            if (itemInventory == null) {
                throw new Exception("ItemInventory is null or empty");
            }
        } catch (Exception e) {
            try {
                itemInventory = DataFetcher.fetchItemInventory(userID);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        Datastore.getInstance().setItemInventory(itemInventory);
        return itemInventory;
    }

    /**
     * Cache the item inventory.
     * @param inventory the item inventory
     * @throws CacheException cache exception
     */
    public void cacheInventory(ItemInventory inventory) throws CacheException {
        if (inventory != null) {
            Cache.writeCache(this.ctx, "data_item_inventory", inventory);
        }
    }

    /**
     * Update and cache the item inventory.
     * @param itemInventory the item inventory
     */
    public void updateAndCache(ItemInventory itemInventory) {
        try {
            if (itemInventory != null) {
                // TODO : update API item inventory
                DataFetcher.addItemInventory(itemInventory);
                Cache.writeCache(this.ctx, "data_item_inventory", Datastore.getInstance().getItemInventory());
            }
        } catch (Exception e) {
            e.printStackTrace();
            }
    }
}
