package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.ItemsInventoryAdapter;

/**
 * Items inventory class.
 */
@JsonAdapter(ItemsInventoryAdapter.class)
public class ItemInventory {

    @SerializedName("data")
    private HashMap<Item, Integer> itemIventoryList;

    public ItemInventory() {
        this.itemIventoryList = new HashMap<>();
    }

    public ItemInventory(HashMap<Item, Integer> itemIventoryList) {
        this.itemIventoryList = itemIventoryList;
    }

    public HashMap<Item, Integer> getItemIventoryList() {
        return itemIventoryList;
    }

    public void addItem(Item item, int quantity) {
        if (itemIventoryList.containsKey(item)) {
            itemIventoryList.put(item, itemIventoryList.get(item) + quantity);
        } else {
            itemIventoryList.put(item, quantity);
        }
    }

    public void removeItem(Item item, int quantity) {
        if (itemIventoryList.containsKey(item)) {
            if (itemIventoryList.get(item) - quantity > 0) {
                itemIventoryList.put(item, itemIventoryList.get(item) - quantity);
            } else {
                itemIventoryList.remove(item);
            }
        }
    }

    public void removeItem(Item item) {
        if (itemIventoryList.containsKey(item)) {
            itemIventoryList.remove(item);
        }
    }

    public void removeItem(int index) {
        if (index < itemIventoryList.size()) {
            itemIventoryList.remove(index);
        }
    }

    public void removeItem() {
        itemIventoryList.clear();
    }

    public int getQuantity(Item item) {
        if (itemIventoryList.containsKey(item)) {
            return itemIventoryList.get(item);
        } else {
            return 0;
        }
    }

    public int getQuantity(int index) {
        if (index < itemIventoryList.size()) {
            return itemIventoryList.get(index);
        } else {
            return 0;
        }
    }

    public Item getItem(int index) {
        if (index < itemIventoryList.size()) {
            return (Item) itemIventoryList.keySet().toArray()[index];
        } else {
            return null;
        }
    }

    public int size() {
        return itemIventoryList.size();
    }
}
