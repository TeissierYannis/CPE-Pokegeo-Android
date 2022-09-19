package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Pokemon API With Retrofit.
 */
public interface PokemonAPI extends BaseAPI {
        /**
        * Get all pokemons.
         */
        @GET("pokemon")
        Call<PokemonList> getPokemons();

    /**
         * Get one item.
         * @param id Item id.
         */
        @GET("pokemon/{id}")
        Call<Item> getItem(@Path("id") int id);
}
