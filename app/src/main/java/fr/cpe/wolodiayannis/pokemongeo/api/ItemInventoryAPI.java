package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;
import fr.cpe.wolodiayannis.pokemongeo.dto.ItemInventoryDto;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
     * Init post of the user item inventory on the API.
     * @param itemInventoryDto the user item inventory
     */
    @POST("inventory/item/post/{userID}")
    Call<BasicResponse> postItemInventory(@Body ItemInventoryDto itemInventoryDto, @Path("userID") int userID);

    /**
     * update the user item inventory on the API.
     * @param itemInventoryDto the user item inventory
     */
    @PUT("inventory/item/update/{userID}")
    Call<BasicResponse> updateItemInventory(@Body ItemInventoryDto itemInventoryDto, @Path("userID") int userID);
}
