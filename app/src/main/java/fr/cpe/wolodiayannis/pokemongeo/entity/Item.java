package fr.cpe.wolodiayannis.pokemongeo.entity;

/**
 * Item class.
 */
public class Item {
    /**
     * Item name.
     */
    private final String name;
    /**
     * Item description.
     */
    private final String description;
    /**
     * Item image id.
     */
    private final int frontResource;
    /**
     * Quantity of item.
     * TODO ?
     */
    private int quantity;

    /**
     * Constructor.
     * @param name Item name.
     * @param description Item description.
     * @param frontResource Item image id.
     */
    public Item(String name, String description, int frontResource) {
        this.name = name;
        this.description = description;
        this.frontResource = frontResource;
        this.quantity = 1;
    }

    /**
     * Factory method.
     * @param name Item name.
     * @param description Item description.
     * @param frontResource Item image id.
     * @return Item.
     */
    public static Item CREATE(
            String name,
            String description,
            int frontResource
    ) {
        return new Item(name, description, frontResource);
    }

    /**
     * Getter.
     * @return Item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter.
     * @return Item description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter.
     * @return Item image id.
     */
    public int getFrontResource() {
        return frontResource;
    }

    /**
     * Getter.
     * @return Quantity of item.
     * TODO ?
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Remove x quantity of item.
     * @param quantity Quantity to remove.
     * @throws RuntimeException If quantity is greater than the quantity of the item.
     * TODO ?
     */
    public void removeQuantity(int quantity) {

        if (this.quantity - quantity < 0) {
            throw new RuntimeException("The quantity is superior to the quantity in the inventory");
        } else {
            this.quantity -= quantity;
        }
    }

    /**
     * Add x quantity of item.
     * @param quantity Quantity to add.
     *  TODO ?
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
