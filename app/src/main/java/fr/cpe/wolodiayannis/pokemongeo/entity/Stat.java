package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.StatAdapter;

@JsonAdapter(StatAdapter.class)
public class Stat {
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

    public Stat(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Stat CREATE(int id, String name) {
        return new Stat(id, name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
