package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.AbilityAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;
import retrofit2.Call;

public class AbilityRequest extends BaseRequest {

    /**
     * Get AbilityAPI.
     * @return AbilityAPI.
     */
    protected static AbilityAPI getAPI() {
        return getRetrofit().create(AbilityAPI.class);
    }

    /**
     * Get all items.
     * @return List of items.
     */
    public static AbilityList getAllAbilities() {
        Call<AbilityList> call = getAPI().getAbilities();

        try {
            AbilityList abilityList = call.execute().body();
            LogAPI("Abilities");
            return abilityList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get one item.
     * @param id Item id.
     * @return Item.
     */
    public static Ability getItemFromID(int id) {
        Call<Ability> call = getAPI().getAbility(id);
        try {
            Ability ability = call.execute().body();
            LogAPI("Ability ID : " + id);
            return ability;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
