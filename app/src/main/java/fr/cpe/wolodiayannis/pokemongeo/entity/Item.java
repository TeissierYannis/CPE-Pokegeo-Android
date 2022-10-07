package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.ItemAdapter;

/**
 * Item class.
 */
@JsonAdapter(ItemAdapter.class)
public class Item implements Serializable {
    /**
     * Item id.
     */
    @SerializedName("id")
    private final int id;
    /**
     * Item name.
     */
    @SerializedName("name")
    private final String name;

    /**
     * Item price.
     */
    @SerializedName("price")
    private final int price;

    /**
     * Item constructor.
     * @param id Item id.
     * @param name Item name.
     */
    public Item(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Get item id.
     * @return Item id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get item name.
     * @return Item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get item price.
     * @return Item price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Check if two items are equals.
     * @param o Item to compare.
     * @return True if equals, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && getName().equals(item.getName()) && getPrice() == item.getPrice();
    }

    /**
     * Hash the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice());
    }
}
