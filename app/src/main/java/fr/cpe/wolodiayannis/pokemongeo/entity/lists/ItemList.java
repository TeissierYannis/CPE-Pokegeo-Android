package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.ItemListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;

@JsonAdapter(ItemListAdapter.class)
public class ItemList implements Serializable {
    /**
     * List of item
     */
    private List<Item> itemList;

    /**
     * ItemList constructor.
     * @param itemList List of item.
     */
    public ItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    /**
     * Get the item list.
     * @return List of item.
     */
    public List<Item> getItemList() {
        return itemList;
    }

    public void addItem(int id, String name, int price) {
        try {
            assert id > 0;
            assert name != null;
            assert price >= 0;
            itemList.add(new Item(id, name, price));
        } catch (AssertionError ignored) {}
    }
}
