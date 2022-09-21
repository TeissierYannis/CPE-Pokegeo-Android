package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.AbilityListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;

@JsonAdapter(AbilityListAdapter.class)
public class AbilityList implements Serializable {
    /**
     * List of ability
     */
    private List<Ability> abilityList;

    /**
     * Constructor.
     * @param abilityList List of ability.
     */
    public AbilityList(List<Ability> abilityList) {
        this.abilityList = abilityList;
    }

    /**
     * Get the ability list.
     * @return List of ability.
     */
    public List<Ability> getAbilityList() {
        return abilityList;
    }
}
