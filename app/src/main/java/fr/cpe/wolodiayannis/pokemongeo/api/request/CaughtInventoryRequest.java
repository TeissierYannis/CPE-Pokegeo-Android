package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;
import fr.cpe.wolodiayannis.pokemongeo.api.CaughtInventoryAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import retrofit2.Call;

public class CaughtInventoryRequest extends BaseRequest {

    /**
     * Get CaughtInvetoryAPI.
     * @return CaughtInventoryAPI.
     */
    protected static CaughtInventoryAPI getAPI() {
        return getRetrofit().create(CaughtInventoryAPI.class);
    }

    /**
     * Get all caught pokemons.
     * @return List of caught pokemons.
     */
    public static CaughtInventory getCaughtInventory(int userID) {
        Call<CaughtInventory> call = getAPI().getCaughtInventory(userID);

        try {
            CaughtInventory caughtInventory = call.execute().body();
            LogAPI("Inventory of user ID : " + userID);
            return caughtInventory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addCaughtPokemon(CaughtPokemon caughtPokemon) {
        boolean res = false;

        Call<Boolean> call = getAPI().addCaughtPokemon(
                caughtPokemon.getUser_id(),
                caughtPokemon.getPokemon_id(),
                caughtPokemon.getPokemon_experience(),
                caughtPokemon.getCurrent_life_state()
        );
        try {
            res = Boolean.TRUE.equals(call.execute().body());
            LogAPI("Add pokemon " + caughtPokemon.getPokemon_id() + " of user ID : " + caughtPokemon.getUser_id());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
