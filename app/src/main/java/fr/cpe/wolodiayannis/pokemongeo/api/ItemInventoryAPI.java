package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Item inventory API With Retrofit.
 */
public interface ItemInventoryAPI extends BaseAPI {
    /**
     * Get all inventory's items.
     */
    @GET("inventory/item/{userID}")
    Call<ItemInventory> getItemInventory(@Path("userID") int userID);

    /**
     * Add item to API inventory.
     */
    Call<ItemInventory> addItem(int userID, int itemID, int quantity);
}
