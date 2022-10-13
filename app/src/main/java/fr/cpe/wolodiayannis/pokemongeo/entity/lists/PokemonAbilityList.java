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
    private HashMap<Integer, List<Integer>> abilities;

    /**
     * Constructor.
     * @param abilityList List of ability.
     */
    public PokemonAbilityList(HashMap<Integer, List<Integer>> abilityList) {
        this.abilities = abilityList;
    }

    /**
     * Get the ability list.
     * @return List of ability.
     */
    public HashMap<Integer, List<Integer>> getAbilityList() {
        return abilities;
    }

    /**
     * Get the ability(ids) list by pokemon id.
     * @param id Pokemon id.
     * @return List of ability.
     */
    public List<Integer> getAbilitiesByPokemonID(int id) {
       // get the list in fact of the id
        return abilities.get(id);
    }
}
