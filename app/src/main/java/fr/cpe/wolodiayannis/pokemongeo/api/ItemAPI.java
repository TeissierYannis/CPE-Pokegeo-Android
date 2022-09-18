package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Item API With Retrofit.
 * The response of the api is a json object
 * {
 *   "message": "success",
 *   "data": [
 *     {
 *       "id": 1,
 *       "name": "master-ball"
 *     }
 *     ...
 *   ]
 * }
 * The data is in the data field inside of an array.
 */
public interface ItemAPI extends BaseAPI {
        /**
        * Get all items.
        * @return List of items.
        */
        @GET("item")
        Call<ItemList> getItems();

        /**
         * Get one item.
         * @param id Item id.
         */
        @GET("item/{id}")
        Call<Item> getItem(@Path("id") int id);
}
