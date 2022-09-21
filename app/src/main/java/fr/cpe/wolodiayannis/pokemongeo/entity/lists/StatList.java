package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.StatAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;

@JsonAdapter(StatAdapter.class)
public class StatList {

    private List<Stat> statsList;

    /**
     * Get the stat list.
     * @return List of stat.
     */
    public List<Stat> getStatsList() {
        return statsList;
    }
}
