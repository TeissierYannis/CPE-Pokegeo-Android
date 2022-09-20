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

    public List<Stat> getStatList() {
        return statList;
    }
}
