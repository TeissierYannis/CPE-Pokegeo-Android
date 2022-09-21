package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.ItemAdapter;

/**
 * Item class.
 */
@JsonAdapter(ItemAdapter.class)
public class Item {
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
     * Item constructor.
     * @param id Item id.
     * @param name Item name.
     */
    public Item(int id, String name) {
        this.id = id;
        this.name = name;
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
     * Check if two items are equals.
     * @param o Item to compare.
     * @return True if equals, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && getName().equals(item.getName());
    }

    /**
     * Hash the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
