package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemBallList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemPotionList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemReviveList;
import retrofit2.Call;
import retrofit2.http.GET;

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
        * Get all ball items.
        * @return List of ball items.
        */
        @GET("item/ball")
        Call<ItemBallList> getBallItems();

        /**
         * get all potion items.
         * @return List of potion items.
         */
        @GET("item/potion")
        Call<ItemPotionList> getPotionItems();

        /**
         * Get revive items.
         * @return List of revive items.
         */
        @GET("item/revive")
        Call<ItemReviveList> getReviveItems();

}
