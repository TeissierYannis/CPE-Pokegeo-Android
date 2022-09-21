package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.ItemAdapter;

/**
 * Ability class.
 */
@JsonAdapter(ItemAdapter.class)
public class Ability implements Serializable {
    /**
     * Ability id.
     */
    @SerializedName("id")
    private final int id;

    /**
     * Ability name.
     */
    @SerializedName("name")
    private final String name;

    /**
     * Ability constructor.
     * @param id Ability id.
     * @param name Ability name.
     */
    public Ability(int id, String name) {
        this.id = id;
        this.name = name;
    }

/**
     * Get ability id.
     * @return Ability id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get ability name.
     * @return Ability name.
     */
    public String getName() {
        return name;
    }

}


