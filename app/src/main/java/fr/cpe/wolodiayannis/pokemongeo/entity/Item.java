package fr.cpe.wolodiayannis.pokemongeo.entity;

public class Item {
    private final String name;
    private final String description;
    private final int frontResource;
    private int quantity;

    public Item(String name, String description, int frontResource) {
        this.name = name;
        this.description = description;
        this.frontResource = frontResource;
        this.quantity = 1;
    }

    public static Item CREATE(
            String name,
            String description,
            int frontResource
    ) {
        return new Item(name, description, frontResource);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getFrontResource() {
        return frontResource;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void removeQuantity(int quantity) {

        if (this.quantity - quantity < 0) {
            throw new RuntimeException("The quantity is superior to the quantity in the inventory");
        } else {
            this.quantity -= quantity;
        }
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
