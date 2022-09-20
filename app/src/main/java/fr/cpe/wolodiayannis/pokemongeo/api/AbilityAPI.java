package fr.cpe.wolodiayannis.pokemongeo.api;


import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
public interface AbilityAPI extends  BaseAPI {

    /**
     * Get all abilities.
     * @return List of abilities.
     */
    @GET("ability")
    Call<AbilityList> getAbilities();

    /**
     * Get one ability.
     * @param id Ability id.
     */
    @GET("ability/{id}")
    Call<AbilityList> getAbility(@Path("id") int id);

    /**
     * Get one ability.
     * @param name Ability name.
     */
    @GET("ability/{name}")
    Call<AbilityList> getAbility(@Path("name") String name);
}

