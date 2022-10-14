package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.PokemonAbilityListAdapter;

@JsonAdapter(PokemonAbilityListAdapter.class)
public class PokemonAbilityList implements Serializable {
    /**
     * List of ability
     */
    private final HashMap<Integer, List<Integer>> abilities;

    /**
     * Constructor.
     *
     * @param abilityList List of ability.
     */
    public PokemonAbilityList(HashMap<Integer, List<Integer>> abilityList) {
        this.abilities = abilityList;
    }

    /**
     * Get the ability list.
     *
     * @return List of ability.
     */
    public HashMap<Integer, List<Integer>> getAbilityList() {
        return abilities;
    }
}
