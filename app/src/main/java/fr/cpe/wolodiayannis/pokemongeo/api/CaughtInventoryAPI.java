package fr.cpe.wolodiayannis.pokemongeo.api;

import java.util.HashMap;

import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    @POST("inventory/caught/{userID}/{pokemonID}/{pokemonExperience}/{currentLifeState}")
    Call<Boolean> addCaughtPokemon(@Path("userID") int userID, @Path("pokemonID") int pokemonID, @Path("pokemonExperience") int pokemonExperience, @Path("currentLifeState") int currentLifeState);
}

