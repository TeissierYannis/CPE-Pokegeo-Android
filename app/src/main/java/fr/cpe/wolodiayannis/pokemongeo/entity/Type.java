package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.TypeAdapter;

/**
 * Type class.
 */
@JsonAdapter(TypeAdapter.class)
public class Type implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Type id.
     */
    @SerializedName("id")
    private final int id;
    /**
     * Type name.
     */
    @SerializedName("name")
    private final String name;

    /**
     * Type constructor.
     * @param id Type id.
     * @param name Type name.
     */
    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get type id.
     * @return Type id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get type name.
     * @return Type name.
     */
    public String getName() {
        return name;
    }
}