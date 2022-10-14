package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.StatListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;

@JsonAdapter(StatListAdapter.class)
public class StatList implements Serializable {

    /**
     * List of stats
     */
    private final List<Stat> statsList;

    /**
     * Constructor.
     *
     * @param statsList List of stats.
     */
    public StatList(List<Stat> statsList) {
        this.statsList = statsList;
    }

    /**
     * Get the stat list.
     *
     * @return List of stat.
     */
    public List<Stat> getStatsList() {
        return statsList;
    }
}
