package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.PokemonTypeListAdapter;

@JsonAdapter(PokemonTypeListAdapter.class)
public class PokemonTypeList implements Serializable {
    /**
     * List of ability
     */
    private final HashMap<Integer, List<Integer>> types;

    /**
     * Constructor.
     *
     * @param types List of types.
     */
    public PokemonTypeList(HashMap<Integer, List<Integer>> types) {
        this.types = types;
    }

    /**
     * Get the ability list.
     *
     * @return List of types.
     */
    public HashMap<Integer, List<Integer>> getTypes() {
        return types;
    }
}
