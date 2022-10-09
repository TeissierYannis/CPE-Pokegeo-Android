package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemBall;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemPotion;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemRevive;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class ItemsFetcher {

    private Context ctx;

    public ItemsFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public List<Item> fetchAndCache() {
        List<Item> itemList = new ArrayList<>();
        try {
            itemList = (List<Item>) Cache.readCache(this.ctx, "data_items");
        } catch (Exception e) {
            try {

                List<ItemBall> itemBallList = DataFetcher.fetchItemBallList().getItemBallList();
                List<ItemRevive> itemReviveList = DataFetcher.fetchItemReviveList().getItemReviveList();
                List<ItemPotion> itemPotionList = DataFetcher.fetchItemPotionList().getItemPotionList();

                itemList.addAll(itemBallList);
                itemList.addAll(itemReviveList);
                itemList.addAll(itemPotionList);

                Cache.writeCache(this.ctx, "data_items", itemList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return itemList;
    }
}
