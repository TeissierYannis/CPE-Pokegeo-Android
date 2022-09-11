package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Item class.
 */@DatabaseTable(tableName = "items")
public class Items {
    /**
     * Item image id.
     */
    @DatabaseField(generatedId = true)
    private final int frontResource;
    /**
     * Item name.
     */
    @DatabaseField
    private final String name;
    /**
     * Item description.
     */
    private final String description;
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
    public Items(String name, String description, int frontResource) {
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
    public static Items CREATE(
            String name,
            String description,
            int frontResource
    ) {
        return new Items(name, description, frontResource);
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
