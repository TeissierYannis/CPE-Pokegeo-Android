package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private final int MAX_ITEMS = 50;
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>(MAX_ITEMS);
    }

    public int getMaxItems() {
        return MAX_ITEMS;
    }

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

    public List<Item> getItems() {
        return this.items;
    }

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

    public int getItemQuantity(Item item) {
        if (this.itemIsInInventory(item)) {
            return this.items.get(this.items.indexOf(item)).getQuantity();
        }

        throw new RuntimeException("The item is not in the inventory");
    }

    private boolean inventoryIsFull() {
        if (this.items.size() >= MAX_ITEMS) {
            throw new RuntimeException("The inventory is full");
        } else {
            return false;
        }
    }

    private boolean itemIsInInventory(Item item) {
        for (Item i : items) {
            if (i.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }
}
