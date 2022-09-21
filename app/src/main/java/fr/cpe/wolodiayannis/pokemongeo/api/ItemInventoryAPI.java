package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Item inventory API With Retrofit.
 */
public interface ItemInventoryAPI extends BaseAPI {
    /**
     * Get all items.
     */
    @GET("inventory/item/{userID}")
    Call<ItemList> getItemInventory(@Path("userID") int userID);
}
