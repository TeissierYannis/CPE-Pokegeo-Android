package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.api.ItemInventoryAPI;
import fr.cpe.wolodiayannis.pokemongeo.api.PokemonAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.ItemList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonAbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonStatList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.TypeList;
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
     * Get one item.
     *
     * @param id Pokemon id.
     * @return Pokemon.
     */
    public static Pokemon getPokemon(int id) {
        Call<Pokemon> call = getAPI().getPokemon(id);

        try {
            Pokemon pokemon = call.execute().body();
            LogAPI("Pokemon");
            return pokemon;
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
     * @param id Pokemon id.
     * @return PokemonType.
     */
    public static TypeList getPokemonType(int id) {
        Call<TypeList> call = getAPI().getTypes(id);

        try {
            TypeList typeList = call.execute().body();
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
     * @param id Pokemon id.
     * @return PokemonStat.
     */
    public static PokemonStatList getPokemonStat(int id) {
        Call<PokemonStatList> call = getAPI().getStats(id);

        try {
            PokemonStatList pokemonStatList = call.execute().body();
            LogAPI("PokemonStat");
            return pokemonStatList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
