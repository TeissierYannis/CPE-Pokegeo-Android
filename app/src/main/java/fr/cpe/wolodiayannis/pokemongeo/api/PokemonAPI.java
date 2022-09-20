package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonStatList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.TypeList;
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
     *
     * @param id Item id.
     */
    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);

    /**
     * Get pokemon abilities
     */
    @GET("pokemon/abilities/{id}")
    Call<AbilityList> getAbilities(@Path("id") int id);

    /**
     * Get pokemon types
     */
    @GET("pokemon/types/{id}")
    Call<TypeList> getTypes(@Path("id") int id);

    /**
     * Get pokemon stats
     */
    @GET("pokemon/stats/{id}")
    Call<PokemonStatList> getStats(@Path("id") int id);
}
