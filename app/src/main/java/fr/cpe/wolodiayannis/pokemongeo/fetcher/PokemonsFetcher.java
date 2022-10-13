package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

/**
 * Pokemon fetcher.
 */
public class PokemonsFetcher {

    /**
     * Context.
     */
    private Context ctx;

    /**
     * Constructor.
     * @param ctx context.
     */
    public PokemonsFetcher(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get pokemons.
     * @return pokemons.
     */
    public List<Pokemon> fetchAndCache() {
        List<Pokemon> pokemonList = new ArrayList<>();
        try {
            pokemonList = (List<Pokemon>) Cache.readCache(this.ctx, "data_pokemons");
        } catch (Exception e) {
            try {
                pokemonList = DataFetcher.fetchPokemonList().getPokemonList();
                Cache.writeCache(this.ctx, "data_pokemons", pokemonList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return pokemonList;
    }
}
