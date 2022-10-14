package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.StatAdapter;

@JsonAdapter(StatAdapter.class)
public class Stat implements Serializable {
    /**
     * Stat ID.
     */
    @SerializedName("id")
    private final int id;

    /**
     * Stat name.
     */
    @SerializedName("name")
    private final String name;

    /**
     * Stat constructor.
     *
     * @param id   stat id.
     * @param name stat name.
     */
    public Stat(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Stat factory.
     *
     * @param id   stat id.
     * @param name stat name.
     * @return Stat instance.
     */
    public static Stat CREATE(int id, String name) {
        return new Stat(id, name);
    }

    /**
     * Get stat id.
     *
     * @return stat id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get stat name.
     *
     * @return stat name.
     */
    public String getName() {
        return name;
    }
}
