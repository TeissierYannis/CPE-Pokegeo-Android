package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Type class.
 */
@JsonAdapter(TypeAdapter.class)
public class Type {
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