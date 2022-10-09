package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.ItemReviveListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemRevive;

@JsonAdapter(ItemReviveListAdapter.class)
public class ItemReviveList {

    private List<ItemRevive> itemReviveList;

    /**
     * Constructor.
     * @param itemReviveList List of item revive.
     */
    public ItemReviveList(List<ItemRevive> itemReviveList) {
        this.itemReviveList = itemReviveList;
    }

    /**
     * Get the item revive list.
     * @return List of item revive.
     */
    public List<ItemRevive> getItemReviveList() {
        return itemReviveList;
    }
}
