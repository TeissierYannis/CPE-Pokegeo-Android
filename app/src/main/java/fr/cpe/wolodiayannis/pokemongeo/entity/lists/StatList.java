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
    private List<Stat> statsList;

    /**
     * Constructor.
     * @param statsList
     */
    public StatList(List<Stat> statsList) {
        this.statsList = statsList;
    }

    /**
     * Get the stat list.
     * @return List of stat.
     */
    public List<Stat> getStatsList() {
        return statsList;
    }

    /**
     * Add a stat to the list.
     * @param id Stat id.
     * @param name Stat name.
     */
    public void addStat(int id, String name) {
        try {
            assert id > 0;
            assert name != null;
            statsList.add(new Stat(id, name));
        } catch (AssertionError ignored) {}
    }
}
