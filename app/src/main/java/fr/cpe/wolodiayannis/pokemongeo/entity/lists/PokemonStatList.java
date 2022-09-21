package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;

public class PokemonStatList {
/**
     * List of stat
     */
    @SerializedName("data")
    private List<Stat> statList;

    /**
     * Get the pokemon stat list.
     * @return List of pokemon stat.
     */
    public List<Stat> getStatList() {
        return statList;
    }
}
