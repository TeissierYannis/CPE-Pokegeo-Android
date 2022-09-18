package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;

public class ItemList {
    /**
     * List of item
     */
    @SerializedName("data")
    private List<Item> itemList;

    public List<Item> getItemList() {
        return itemList;
    }
}
