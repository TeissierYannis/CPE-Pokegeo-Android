package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;

public class AbilityList {
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
