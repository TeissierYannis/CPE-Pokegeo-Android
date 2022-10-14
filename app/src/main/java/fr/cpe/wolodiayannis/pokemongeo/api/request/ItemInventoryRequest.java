package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.ItemInventoryAPI;
import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;
import fr.cpe.wolodiayannis.pokemongeo.dto.ItemInventoryDto;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;
import retrofit2.Call;

public class ItemInventoryRequest extends BaseRequest {

    /**
     * Get ItemInventoryAPI.
     *
     * @return ItemInventoryAPI.
     */
    protected static ItemInventoryAPI getAPI() {
        return getRetrofit().create(ItemInventoryAPI.class);
    }

    /**
     * Get all all item in inventory.
     *
     * @return List of items in inventory.
     */
    public static ItemInventory getItemInventory(int userID) {
        Call<ItemInventory> call = getAPI().getItemInventory(userID);

        try {
            ItemInventory itemInventory = call.execute().body();
            LogAPI("Inventory of user ID : " + userID);
            return itemInventory;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Init post of the user item inventory on the API.
     *
     * @param itemInventory the user item inventory
     */
    public static void postItemInventory(ItemInventory itemInventory) {
        Call<BasicResponse> call = getAPI().postItemInventory(new ItemInventoryDto(itemInventory));
        try {
            call.execute();
            LogAPI("Post inventory of user on API");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * update the user item inventory on the API.
     *
     * @param itemInventory the user item inventory.
     */
    public static void updateItemInventory(ItemInventory itemInventory) {
        Call<BasicResponse> call = getAPI().updateItemInventory(new ItemInventoryDto(itemInventory));
        try {
            call.execute();
            LogAPI("Update inventory of user on API");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
