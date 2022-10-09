package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemBall;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemPotion;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemRevive;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class ItemsFetcher {

    private Context ctx;

    public ItemsFetcher(Context ctx) {
        this.ctx = ctx;
    }


    /**
     * Fetches the ball item list from the API.
     * @return the ball item list.
     */
    public List<ItemBall> fetchBall() {

        List<ItemBall> itemBallList = new ArrayList<>();
        try {
            itemBallList = (List<ItemBall>) Cache.readCache(this.ctx, "data_items_ball");
        } catch (Exception e) {
            try {
                itemBallList = DataFetcher.fetchItemBallList().getItemBallList();
                Cache.writeCache(this.ctx, "data_items_ball", itemBallList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return itemBallList;
    }

    /**
     * Fetches the potion item list from the API.
     * @return the potion item list.
     */
    public List<ItemPotion> fetchPotion() {

        List<ItemPotion> itemPotionList = new ArrayList<>();
        try {
            itemPotionList = (List<ItemPotion>) Cache.readCache(this.ctx, "data_items_potion");
        } catch (Exception e) {
            try {
                itemPotionList = DataFetcher.fetchItemPotionList().getItemPotionList();
                Cache.writeCache(this.ctx, "data_items_potion", itemPotionList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return itemPotionList;
    }

    /**
     * Fetches the revive item list from the API.
     * @return the revive item list.
     */
    public List<ItemRevive> fetchRevive() {

        List<ItemRevive> itemReviveList = new ArrayList<>();
        try {
            itemReviveList = (List<ItemRevive>) Cache.readCache(this.ctx, "data_items_revive");
        } catch (Exception e) {
            try {
                itemReviveList = DataFetcher.fetchItemReviveList().getItemReviveList();
                Cache.writeCache(this.ctx, "data_items_revive", itemReviveList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return itemReviveList;
    }
}
