package fr.cpe.wolodiayannis.pokemongeo.datas;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonAbilities;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonDescription;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonExperienceLevel;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonGeneration;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStats;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonType;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.UserExperienceLevel;

public class EnumList {

    public static List<Type> types;
    public static List<Item> items;
    public static List<Stat> stats;
    public static List<Pokemon> pokemons;
    public static List<Ability> abilities;
    public static List<PokemonType> pokemonsTypes;
    public static List<PokemonStats> pokemonsStats;
    public static List<PokemonAbilities> pokemonsAbilities;
    public static List<PokemonGeneration> pokemonsGeneration;
    public static List<PokemonDescription> pokemonsDescription;
    public static List<UserExperienceLevel> usersExperienceLevel;
    public static List<PokemonExperienceLevel> pokemonsExperienceLevel;

    public static List<Type> getTypes() {
        return types;
    }

    public static void addType(Type type) {
        types.add(type);
    }
    public static List<Item> getItems() {
        return items;
    }

    public static void addItem(Item item) {
        EnumList.items.add(item);
    }

    public static List<Stat> getStats() {
        return stats;
    }

    public static void addStat(Stat stat) {
        EnumList.stats.add(stat);
    }

    public static List<Pokemon> getPokemons() {
        return pokemons;
    }

    public static void addPokemon(Pokemon pokemon) {
        EnumList.pokemons.add(pokemon);
    }

    public static List<Ability> getAbilities() {
        return abilities;
    }

    public static void addAbility(Ability ability) { abilities.add(ability);}

    public static List<PokemonType> getPokemonsTypes() { return pokemonsTypes; }

    public static void addPokemonType(PokemonType pokemonType) {
        pokemonsTypes.add(pokemonType);
    }

    public static List<PokemonStats> getPokemonsStats() {
        return pokemonsStats;
    }

    public static void addPokemonStats(PokemonStats pokemonStats) {
        pokemonsStats.add(pokemonStats);
    }

    public static List<PokemonAbilities> getPokemonsAbilities() { return pokemonsAbilities; }

    public static void addPokemonAbility(PokemonAbilities pokemonAbility) {
        pokemonsAbilities.add(pokemonAbility);
    }

    public static List<PokemonGeneration> getPokemonsGeneration() {
        return pokemonsGeneration;
    }

    public static void addPokemonGeneration(PokemonGeneration pokemonGeneration) {
        EnumList.pokemonsGeneration.add(pokemonGeneration);
    }

    public static List<PokemonDescription> getPokemonsDescription() {
        return pokemonsDescription;
    }

    public static void addPokemonDescription(PokemonDescription pokemonDescription) {
        EnumList.pokemonsDescription.add(pokemonDescription);
    }

    public static List<UserExperienceLevel> getUsersExperienceLevel() {
        return usersExperienceLevel;
    }

    public static void addUserExperienceLevel(UserExperienceLevel userExperienceLevel) {
        EnumList.usersExperienceLevel.add(userExperienceLevel);
    }

    public static List<PokemonExperienceLevel> getPokemonsExperienceLevel() {
        return pokemonsExperienceLevel;
    }

    public static void addPokemonExperienceLevel(PokemonExperienceLevel pokemonExperienceLevel) {
        EnumList.pokemonsExperienceLevel.add(pokemonExperienceLevel);
    }
}
