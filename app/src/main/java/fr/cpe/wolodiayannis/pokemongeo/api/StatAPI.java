package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.StatList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Pokemon API With Retrofit.
 */
public interface StatAPI extends BaseAPI {
    /**
     * Get all stats.
     */
    @GET("stat")
    Call<StatList> getStats();

    /**
     * Get one stat.
     *
     * @param id stat id.
     */
    @GET("stat/{id}")
    Call<Item> getStat(@Path("id") int id);
}
