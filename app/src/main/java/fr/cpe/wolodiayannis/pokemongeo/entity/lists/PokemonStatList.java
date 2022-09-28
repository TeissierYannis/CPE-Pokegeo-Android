package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.PokemonStatMappingListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;

@JsonAdapter(PokemonStatMappingListAdapter.class)
public class PokemonStatList implements Serializable {
/**
     * List of stat
     */
    private HashMap<Integer, List<PokemonStat>> stats;

    /**
     * Constructor.
     * @param statList List of stat.
     */
    public PokemonStatList(HashMap<Integer, List<PokemonStat>> statList) {
        this.stats = statList;
    }

    /**
     * Get the stat list.
     * @return List of stat.
     */
    public HashMap<Integer, List<PokemonStat>> getStatList() {
        return stats;
    }

    public List<PokemonStat> getStatsByPokemonID(int id) {
        // get the list in fact of the id
        return stats.get(id);
    }
}
