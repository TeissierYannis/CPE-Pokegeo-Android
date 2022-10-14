package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.PokemonAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonAbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonStatMappingList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonTypeList;
import retrofit2.Call;

public class PokemonRequest extends BaseRequest {

    /**
     * Get ItemInventoryAPI.
     *
     * @return ItemInventoryAPI.
     */
    protected static PokemonAPI getAPI() {
        return getRetrofit().create(PokemonAPI.class);
    }

    /**
     * Get all items.
     *
     * @return List of items.
     */
    public static PokemonList getPokemons() {
        Call<PokemonList> call = getAPI().getPokemons();

        try {
            PokemonList pokemonList = call.execute().body();
            LogAPI("Pokemons");
            return pokemonList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get pokemonAbility
     *
     * @return PokemonAbility.
     */
    public static PokemonAbilityList getPokemonAbilities() {
        Call<PokemonAbilityList> call = getAPI().getAbilities();

        try {
            PokemonAbilityList abilityList = call.execute().body();
            LogAPI("PokemonAbility");
            return abilityList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get pokemonType
     *
     * @return PokemonType.
     */
    public static PokemonTypeList getPokemonTypes() {
        Call<PokemonTypeList> call = getAPI().getTypes();

        try {
            PokemonTypeList typeList = call.execute().body();
            LogAPI("PokemonType");
            return typeList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get pokemonStat
     *
     * @return PokemonStat.
     */
    public static PokemonStatMappingList getPokemonStat() {
        Call<PokemonStatMappingList> call = getAPI().getStats();

        try {
            PokemonStatMappingList pokemonStatList = call.execute().body();
            LogAPI("PokemonStat");
            return pokemonStatList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
