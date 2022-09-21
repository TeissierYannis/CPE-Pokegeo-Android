package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;

public class AbilityList {
    /**
     * List of ability
     */
    @SerializedName("data")
    private List<Ability> abilityList;


    /**
     * Get the ability list.
     * @return List of ability.
     */
    public List<Ability> getAbilityList() {
        return abilityList;
    }
}
