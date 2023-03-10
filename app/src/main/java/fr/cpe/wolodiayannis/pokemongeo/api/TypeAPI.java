package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.lists.TypeList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Type API With Retrofit.
 * The response of the api is a json object
 * {
 *      "message": "success",
 *      "data": [
 *          {
 *              "id": 1,
 *              "name": "normal"
 *          }
 *          ...
 *      ]
 * }
 * The data is in the data field inside of an array.
 */
public interface TypeAPI extends BaseAPI {
    /**
     * Get all types.
     *
     * @return List of types.
     */
    @GET("type")
    Call<TypeList> getTypes();
}
