package fr.cpe.wolodiayannis.pokemongeo.api;

import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonAbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonStatList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonTypeList;
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
    @GET("pokemon/abilities/all")
    Call<PokemonAbilityList> getAbilities();

    /**
     * Get pokemon types
     */
    @GET("pokemon/types/all")
    Call<PokemonTypeList> getTypes();

    /**
     * Get pokemon stats
     */
    @GET("pokemon/stats/{id}")
    Call<PokemonStatList> getStats(@Path("id") int id);
}
