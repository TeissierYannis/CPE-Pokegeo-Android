package fr.cpe.wolodiayannis.pokemongeo.entity;

/**
 * Inventory class.
 */
public class ItemsInventory {

    /**
     * Max slots in the inventory.
     */
    private final int MAX_ITEMS = 50;

    private final int user_id;
    private final int item_id;
    private int quantity;

    public ItemsInventory(int user_id, int item_id, int quantity) {
        this.user_id = user_id;
        this.item_id = item_id;
        this.quantity = quantity;
    }

    /**
     * Get max items.
     * @return max items
     */
    public int getMaxItems() {
        return MAX_ITEMS;
    }

    /**
     * Get user id.
     * @return user id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Get item id.
     * @return item id
     */
    public int getItem_id() {
        return item_id;
    }

    /**
     * Get quantity.
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set quantity.
     * @param quantity quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Add quantity.
     * @param quantity quantity
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    /**
     * Remove quantity.
     * @param quantity quantity
     */
    public void removeQuantity(int quantity) {
        this.quantity -= quantity;
    }
}
