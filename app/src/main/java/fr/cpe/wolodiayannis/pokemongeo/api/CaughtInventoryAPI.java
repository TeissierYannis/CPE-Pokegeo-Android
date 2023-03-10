package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Caught inventory API With Retrofit.
 */
public interface CaughtInventoryAPI extends BaseAPI {
    /**
     * Get all caught pokemons.
     */
    @GET("inventory/caught/{userID}")
    Call<CaughtInventory> getCaughtInventory(@Path("userID") int userID);

    /**
     * add a pokemon to the caught inventory.
     */
    @POST("inventory/caught")
    Call<BasicResponse> addCaughtPokemon(@Body CaughtPokemon body);

    @PUT("inventory/caught/life/{userID}/{pokemonID}/{currentLifeState}")
    Call<BasicResponse> updateCaughtPokemon(@Path("userID") int userID, @Path("pokemonID") int pokemonID, @Path("currentLifeState") int currentLifeState);
}

