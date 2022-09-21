package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.ItemAPI;
import fr.cpe.wolodiayannis.pokemongeo.api.ItemInventoryAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import retrofit2.Call;

public class ItemInventoryRequest extends BaseRequest {

    /**
     * Get ItemInventoryAPI.
     * @return ItemInventoryAPI.
     */
    protected static ItemInventoryAPI getAPI() {
        return getRetrofit().create(ItemInventoryAPI.class);
    }

    /**
     * Get all items.
     * @return List of items.
     */
    public static ItemList getItemInventory(int userID) {
        Call<ItemList> call = getAPI().getItemInventory(userID);

        try {
            ItemList itemList = call.execute().body();
            LogAPI("Inventory of user ID : " + userID);
            return itemList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
