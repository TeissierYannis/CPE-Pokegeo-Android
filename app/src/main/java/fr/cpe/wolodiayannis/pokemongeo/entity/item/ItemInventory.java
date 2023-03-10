package fr.cpe.wolodiayannis.pokemongeo.entity.item;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.ItemInventoryAdapter;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;

/**
 * Items inventory class.
 */
@JsonAdapter(ItemInventoryAdapter.class)
public class ItemInventory implements Serializable {

    /**
     * Inventory list.
     */
    @SerializedName("data")
    private HashMap<Item, Integer> itemIventoryList;

    /**
     * Constructor.
     */
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
     * Set the item inventory list.
     *
     * @param hm The item inventory hasmap
     */
    public void setItemIventoryList(HashMap<Item, Integer> hm) {
        this.itemIventoryList = hm;
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
     * Get the item inventory size.
     *
     * @return the item inventory size
     */
    public int size() {
        return itemIventoryList.size();
    }

    /**
     * Get possed items.
     *
     * @return the possed items
     */
    public ItemInventory getPossedItems() {
        ItemInventory itemInventoryPossedItem = new ItemInventory();
        // add only item with quantity > 0
        for (Item item : Datastore.getInstance().getItemInventory().getItemIventoryList().keySet()) {
            int quantity = Objects.requireNonNull(Datastore.getInstance().getItemInventory().getItemIventoryList().get(item));
            if (quantity > 0) {
                itemInventoryPossedItem.addItem(
                        item,
                        quantity
                );
            }
        }
        return itemInventoryPossedItem;
    }
}
