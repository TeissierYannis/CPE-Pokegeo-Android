package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;
import fr.cpe.wolodiayannis.pokemongeo.api.CaughtInventoryAPI;
import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;
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

        Call<BasicResponse> call = getAPI().addCaughtPokemon(
                caughtPokemon
        );
        try {
            call.execute();
            LogAPI("Add pokemon " + caughtPokemon.getPokemonId() + " of user ID : " + caughtPokemon.getUserId());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
