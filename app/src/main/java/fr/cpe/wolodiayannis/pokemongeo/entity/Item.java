package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

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
    private int id;
    /**
     * Item name.
     */
    @SerializedName("name")
    private String name;

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
}
