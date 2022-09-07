package fr.cpe.wolodiayannis.pokemongeo.Entity;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;

public class Pokemon {

    private final int ID;
    private final String name;
    private final String species;
    private final List<POKEMON_TYPE> types;
    private final float height;
    private final float weight;
    private final List<POKEMON_ABILITIES> abilities;
    private final Stats stats;
    private final List<Pokemon> evolutions;
    private final String description;
    private final int gen;
    private final int imgID;

    public Pokemon(
            int ID,
            String name,
            String species,
            List<POKEMON_TYPE> types,
            float height, float weight,
            List<POKEMON_ABILITIES> abilities,
            Stats stats,
            List<Pokemon> evolutions,
            String description,
            int gen,
            int imgID) {
        this.ID = ID;
        this.name = name;
        this.species = species;
        this.types = types;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.stats = stats;
        this.evolutions = evolutions;
        this.description = description;
        this.gen = gen;
        this.imgID = imgID;
    }

    public static Pokemon CREATE(
            int ID,
            String name,
            String species,
            List<POKEMON_TYPE> types,
            float height,
            float weight,
            List<POKEMON_ABILITIES> abilities,
            int hp,
            int attack,
            int defense,
            int spAttack,
            int spDefense,
            int speed,
            List<Pokemon> evolutions,
            String description,
            int gen,
            int imgID) {
        return new Pokemon(
                ID,
                name,
                species,
                types,
                height,
                weight,
                abilities,
                Stats.CREATE(hp, attack, defense, spAttack, spDefense, speed),
                evolutions,
                description,
                gen,
                imgID
        );
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public List<POKEMON_TYPE> getTypes() {
        return types;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public List<POKEMON_ABILITIES> getAbilities() {
        return abilities;
    }

    public Stats getStats() {
        return stats;
    }

    public List<Pokemon> getEvolutions() {
        return evolutions;
    }

    public String getDescription() {
        return description;
    }

    public int getGen() {
        return gen;
    }

    public int getFrontResource() {
        return this.imgID;
    }
}