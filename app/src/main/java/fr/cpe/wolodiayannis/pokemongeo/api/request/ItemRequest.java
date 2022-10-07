package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.ItemAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import retrofit2.Call;

public class ItemRequest extends BaseRequest {

    /**
     * Get ItemAPI.
     * @return ItemAPI.
     */
    protected static ItemAPI getAPI() {
        return getRetrofit().create(ItemAPI.class);
    }

    /**
     * Get all items.
     * @return List of items.
     */
    public static ItemList getAllItems() {
        Call<ItemList> call = getAPI().getItems();

        try {
            ItemList itemList = call.execute().body();
            LogAPI("Items");
            return itemList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get one item.
     * @param id Item id.
     * @return Item.
     */
    public static Item getItemFromID(int id) {
        Call<Item> call = getAPI().getItem(1);
        try {
            Item item = call.execute().body();
            LogAPI("Item");
            return item;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
