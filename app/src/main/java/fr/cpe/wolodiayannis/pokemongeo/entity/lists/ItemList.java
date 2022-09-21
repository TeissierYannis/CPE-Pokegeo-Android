package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;

public class ItemList {
    /**
     * List of item
     */
    private List<Item> itemList;

    /**
     * Get the item list.
     * @return List of item.
     */
    public List<Item> getItemList() {
        return itemList;
    }
}
