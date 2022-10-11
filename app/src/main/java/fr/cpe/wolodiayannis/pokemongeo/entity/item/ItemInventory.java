package fr.cpe.wolodiayannis.pokemongeo.entity.item;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.ItemInventoryAdapter;

/**
 * Items inventory class.
 */
@JsonAdapter(ItemInventoryAdapter.class)
public class ItemInventory implements Serializable {

    @SerializedName("data")
    private HashMap<Item, Integer> itemIventoryList;

    public ItemInventory() {
        this.itemIventoryList = new HashMap<>();
    }

    /**
     * Constructor.
     * set the item inventory HashMap.
     *
     * @param itemIventoryList The item inventory list
     */
    public ItemInventory(HashMap<Item, Integer> itemIventoryList) {
        this.itemIventoryList = itemIventoryList;
    }

    /**
     * Get the item inventory list.
     *
     * @return The item inventory list.
     */
    public HashMap<Item, Integer> getItemIventoryList() {
        return itemIventoryList;
    }

    /**
     * Add an item to the inventory.
     *
     * @param item     the item to add
     * @param quantity the quantity of the item to add
     */
    public void addItem(Item item, int quantity) {
        if (itemIventoryList.containsKey(item)) {
            itemIventoryList.put(item, Objects.requireNonNull(itemIventoryList.get(item)) + quantity);
        } else {
            itemIventoryList.put(item, quantity);
        }
    }

    /**
     * Remove an item from the inventory.
     *
     * @param item     the item to remove
     * @param quantity the quantity of the item to remove
     */
    public void removeItem(Item item, int quantity) {
        if (itemIventoryList.containsKey(item)) {
            if (Objects.requireNonNull(itemIventoryList.get(item)) - quantity > 0) {
                itemIventoryList.put(item, Objects.requireNonNull(itemIventoryList.get(item)) - quantity);
            } else {
                itemIventoryList.remove(item);
            }
        }
    }

    /**
     * Completely remove an item from the inventory.
     *
     * @param item the item to remove
     */
    public void removeItem(Item item) {
        if (itemIventoryList.containsKey(item)) {
            itemIventoryList.remove(item);
        }
    }

    /**
     * Completely remove an item from the inventory.
     *
     * @param index the index of the item to remove
     */
    public void removeItem(int index) {
        if (index < itemIventoryList.size()) {
            itemIventoryList.remove(index);
        }
    }

    /**
     * remove all items from the inventory.
     */
    public void removeItem() {
        itemIventoryList.clear();
    }

    /**
     * Get the quantity of an item in the inventory.
     *
     * @param item the item to check
     * @return the quantity of the item in the inventory
     */
    public int getQuantity(Item item) {
        if (itemIventoryList.containsKey(item)) {
            return Objects.requireNonNull(itemIventoryList.get(item));
        } else {
            return 0;
        }
    }

    /**
     * Get the item at the given index.
     *
     * @param index the index of the item to get
     * @return the item at the given index
     */
    public Item getItem(int index) {
        if (index < itemIventoryList.size()) {
            return (Item) itemIventoryList.keySet().toArray()[index];
        } else {
            return null;
        }
    }

    /**
     * Set the item at the given index.
     *
     * @param index the index of the item to set
     * @param item  the item to set
     */
    public void setItem(int index, Item item) {
        if (index < itemIventoryList.size()) {
            itemIventoryList.keySet().toArray()[index] = item;
        }
    }

    /**
     * Get the item inventory size.
     *
     * @return the item inventory size
     */
    public int size() {
        return itemIventoryList.size();
    }
}
