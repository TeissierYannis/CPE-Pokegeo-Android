package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.ItemAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemBallList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemPotionList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemReviveList;
import retrofit2.Call;

public class ItemRequest extends BaseRequest {

    /**
     * Get ItemAPI.
     *
     * @return ItemAPI.
     */
    protected static ItemAPI getAPI() {
        return getRetrofit().create(ItemAPI.class);
    }


    /**
     * Get all ball items.
     *
     * @return List of ball items.
     */
    public static ItemBallList getItemBallList() {
        Call<ItemBallList> call = getAPI().getBallItems();
        try {
            ItemBallList itemBallList = call.execute().body();
            LogAPI("ItemBallRequest");
            return itemBallList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all potion items.
     *
     * @return List of potion items.
     */
    public static ItemPotionList getItemPotionList() {
        Call<ItemPotionList> call = getAPI().getPotionItems();
        try {
            ItemPotionList itemPotionList = call.execute().body();
            LogAPI("ItemPotionRequest");
            return itemPotionList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all revive items.
     *
     * @return List of revive items.
     */
    public static ItemReviveList getItemReviveList() {
        Call<ItemReviveList> call = getAPI().getReviveItems();
        try {
            ItemReviveList itemReviveList = call.execute().body();
            LogAPI("ItemReviveRequest");
            return itemReviveList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
