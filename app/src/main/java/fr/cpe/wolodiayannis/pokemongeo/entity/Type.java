package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import fr.cpe.wolodiayannis.pokemongeo.Enum.BACKGROUND_COLOR;
import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.TypeAdapter;

/**
 * Type class.
 */
@JsonAdapter(TypeAdapter.class)
public class Type implements Serializable {

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
     * Color.
     */
    private final int color;

    /**
     * Type constructor.
     * @param id Type id.
     * @param name Type name.
     */
    public Type(int id, String name) {
        this.id = id;
        this.name = name;
        // Name with first letter in uppercase.
        int col = BACKGROUND_COLOR.Unknown;
        try {
            col = BACKGROUND_COLOR.valueOf(name.substring(0, 1).toUpperCase() + name.substring(1));
        } catch (IllegalArgumentException ignored) {
        }
        this.color = col;
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

    /**
     * Get type color.
     * @return Type color.
     */
    public int getColor() {
        return color;
    }
}