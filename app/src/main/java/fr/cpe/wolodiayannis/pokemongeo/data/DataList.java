package fr.cpe.wolodiayannis.pokemongeo.data;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.PokemonList;

public class DataList {

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

    public DataList(List<Pokemon> pokemons, List<Item> items, List<Stat> stats, List<Type> types, List<Ability> abilities) {
        this.pokemons = pokemons;
        this.items = items;
        this.stats = stats;
        this.types = types;
        this.abilities = abilities;
    }

    public static DataList CREATE(List<Pokemon> pokemons, List<Item> items, List<Stat> stats, List<Type> types, List<Ability> abilities) {
        return new DataList(pokemons, items, stats, types, abilities);
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Stat> getStats() { return stats; }

    public List<Type> getTypes() { return types; }

    public List<Ability> getAbilities() { return abilities; }
}
