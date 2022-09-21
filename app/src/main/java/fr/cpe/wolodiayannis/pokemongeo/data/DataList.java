package fr.cpe.wolodiayannis.pokemongeo.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;

public class DataList implements Serializable {

    private static final long serialVersionUID = 1L;

    // serializable lists
    public final List<Type> types;
    public final List<Item> items;
    public final List<Stat> stats;
    public final List<Pokemon> pokemons;
    public final List<Ability> abilities;
    // public static final List<PokemonType> pokemonsTypes;
    // public static final List<PokemonStats> pokemonsStats;
    // public static final List<PokemonAbilities> pokemonsAbilities;
    // public static final List<PokemonGeneration> pokemonsGeneration;
    // public static final List<PokemonDescription> pokemonsDescription;
    // public static final List<UserExperienceLevel> usersExperienceLevel;
    // public static final List<PokemonExperienceLevel> pokemonsExperienceLevel;

    /**
     * Constructor.
     * @param types List of types.
     * @param items List of items.
     * @param stats List of stats.
     * @param pokemons List of pokemons.
     * @param abilities List of abilities.
     */
    public DataList(List<Pokemon> pokemons, List<Item> items, List<Stat> stats, List<Type> types, List<Ability> abilities) {
        this.pokemons = pokemons;
        this.items = items;
        this.stats = stats;
        this.types = types;
        this.abilities = abilities;
    }

    /**
     * DataList factory.
     * @param types List of types.
     * @param items List of items.
     * @param stats List of stats.
     * @param pokemons List of pokemons.
     * @param abilities List of abilities.
     * @return DataList instance.
     */
    public static DataList CREATE(List<Pokemon> pokemons, List<Item> items, List<Stat> stats, List<Type> types, List<Ability> abilities) {
        return new DataList(pokemons, items, stats, types, abilities);
    }

    /**
     * Get the pokemons list.
     */
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    /**
     * Get the items list.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Get the stats list.
     */
    public List<Stat> getStats() { return stats; }

    /**
     * Get the types list.
     */
    public List<Type> getTypes() { return types; }

    /**
     * Get the abilities list.
     */
    public List<Ability> getAbilities() { return abilities; }

    /**
     * Serialize the DataList instance.
     */
    @Override
    public String toString() {
        return "DataList{" +
                "types=" + types +
                ", items=" + items +
                ", stats=" + stats +
                ", pokemons=" + pokemons +
                ", abilities=" + abilities +
                '}';
    }
}
