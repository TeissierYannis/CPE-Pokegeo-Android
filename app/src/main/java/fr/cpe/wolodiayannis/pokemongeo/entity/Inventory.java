package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory class.
 */
public class Inventory {

    /**
     * Max slots in the inventory.
     */
    private final int MAX_ITEMS = 50;

    /**
     * List of items in the inventory.
     */
    private final List<Item> items;

    /**
     * Constructor.
     */
    public Inventory() {
        this.items = new ArrayList<>(MAX_ITEMS);
    }

    /**
     * Get max items.
     * @return max items
     */
    public int getMaxItems() {
        return MAX_ITEMS;
    }

    /**
     * Get items by index.
     * @param position index
     * @throws InternalError if index is out of bounds
     * @return item
     */
    public Item getItem(int position) {
        if (position < 0 || position >= MAX_ITEMS) {
            throw new InternalError("The position is out of the inventory");
        } else {
            if (position < items.size()) {
                return items.get(position);
            } else {
                return null;
            }
        }
    }

    /**
     * Get item list
     * @return item list
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * Add item to the inventory.
     * @param item item to add
     * @param quantity quantity of the item to add
     * @return instance for chaining.
     */
    public Inventory addItem(Item item, int quantity) {
        if (this.itemIsInInventory(item)) {
            this.items.get(this.items.indexOf(item)).addQuantity(quantity);
        } else {
            if (!this.inventoryIsFull()) {
                item.addQuantity(quantity);
                this.items.add(item);
            }
        }

        return this;
    }

    /**
     * Remove item from the inventory.
     * @param item item to remove
     * @param quantity quantity of the item to remove
     * @throws RuntimeException if the item is not in the inventory
     * @return instance for chaining.
     */
    public Inventory removeItem(Item item, int quantity) {
        if (this.itemIsInInventory(item)) {
            this.items.get(this.items.indexOf(item)).removeQuantity(quantity);
            if (this.items.get(this.items.indexOf(item)).getQuantity() == 0) {
                this.items.remove(item);
            }
        } else {
            throw new RuntimeException("The item is not in the inventory");
        }

        return this;
    }

    /**
     * Get the quantity of one item
     * @param item item to get the quantity
     * @throws RuntimeException if the item is not in the inventory
     * @return quantity of the item
     */
    public int getItemQuantity(Item item) {
        if (this.itemIsInInventory(item)) {
            return this.items.get(this.items.indexOf(item)).getQuantity();
        }

        throw new RuntimeException("The item is not in the inventory");
    }

    /**
     * Check if the inventory is full.
     * @throws RuntimeException if the inventory is full
     * @return false if not full
     */
    private boolean inventoryIsFull() {
        if (this.items.size() >= MAX_ITEMS) {
            throw new RuntimeException("The inventory is full");
        } else {
            return false;
        }
    }

    /**
     * Check if the item is in the inventory.
     * @param item item to check
     * @return true if the item is in the inventory
     */
    private boolean itemIsInInventory(Item item) {
        for (Item i : items) {
            if (i.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }
}
