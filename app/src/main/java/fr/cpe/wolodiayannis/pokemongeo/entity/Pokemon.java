package fr.cpe.wolodiayannis.pokemongeo.entity;

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
    private final int color;
    private int quantity;

    public Pokemon() {
        this.ID = 0;
        this.name = "";
        this.species = "";
        this.types = null;
        this.height = 0;
        this.weight = 0;
        this.abilities = null;
        this.stats = null;
        this.evolutions = null;
        this.description = "";
        this.gen = 0;
        this.imgID = 0;
        this.color = 0;
    }

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
        this.color = this.generateColor();
    }

    public static Pokemon CREATE(
            int ID,
            String name,
            String species,
            List<POKEMON_TYPE> types,
            float height,
            float weight,
            List<POKEMON_ABILITIES> abilities,
            Stats stats,
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
                stats,
                evolutions,
                description,
                gen,
                imgID
        );
    }

    public int generateColor() {
        switch (this.types.get(0)) {
            case Normal:
                return 0xFFA8A878;
            case Fire:
                return 0xFFF08030;
            case Water:
                return 0xFF6890F0;
            case Electric:
                return 0xFFF8D030;
            case Grass:
                return 0xFF78C850;
            case Ice:
                return 0xFF98D8D8;
            case Fighting:
                return 0xFFC03028;
            case Poison:
                return 0xFFA040A0;
            case Ground:
                return 0xFFE0C068;
            case Flying:
                return 0xFFA890F0;
            case Psychic:
                return 0xFFF85888;
            case Bug:
                return 0xFFA8B820;
            case Rock:
                return 0xFFB8A038;
            case Ghost:
                return 0xFF705898;
            case Dragon:
                return 0xFF7038F8;
            case Dark:
                return 0xFF705848;
            case Steel:
                return 0xFFB8B8D0;
            case Fairy:
                return 0xFFEE99AC;
            default:
                return 0xFF000000;
        }
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

    public int getColor() {
        return this.color;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity) {

        if (this.quantity - quantity < 0) {
            throw new RuntimeException("The quantity is superior to the quantity in the inventory");
        } else {
            this.quantity -= quantity;
        }
    }
}