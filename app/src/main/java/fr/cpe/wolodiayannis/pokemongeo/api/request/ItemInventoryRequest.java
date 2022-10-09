package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.ItemInventoryAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;
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
     * Get all all item in inventory.
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

    public static void addItem(int userID, int itemID, int quantity) {
        Call<ItemInventory> call = getAPI().addItem(userID, itemID, quantity);

        try {
            call.execute();
            LogAPI("Add " + quantity + " item " + itemID + " of user ID : " + userID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
