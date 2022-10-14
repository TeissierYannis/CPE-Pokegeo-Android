package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.lists.StatList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Pokemon API With Retrofit.
 */
public interface StatAPI extends BaseAPI {
    /**
     * Get all stats.
     */
    @GET("stat")
    Call<StatList> getStats();
}
