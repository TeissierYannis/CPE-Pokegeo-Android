package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Ability API With Retrofit.
 * The response of the api is a json object
 * {
 *  "message": "success",
 *  "data": [
 *      {
 *          "id": 1,
 *          "name": "stench"
 *      }
 *      ...
 *    ]
 *  }
 *  The data is in the data field inside of an array.
 */
public interface AbilityAPI extends BaseAPI {

    /**
     * Get all abilities.
     *
     * @return List of abilities.
     */
    @GET("ability")
    Call<AbilityList> getAbilities();
}

