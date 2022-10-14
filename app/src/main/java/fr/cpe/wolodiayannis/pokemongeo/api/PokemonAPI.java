package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonAbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonStatMappingList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonTypeList;
import retrofit2.Call;
import retrofit2.http.GET;

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
    @GET("pokemon/stats/all")
    Call<PokemonStatMappingList> getStats();
}
