package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.ItemAdapter;

/**
 * Item class.
 */
@JsonAdapter(ItemAdapter.class)
public class Item {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public Item(){}

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
