package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Enum.BACKGROUND_COLOR;
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
                return BACKGROUND_COLOR.Normal;
            case Fire:
                return BACKGROUND_COLOR.Fire;
            case Water:
                return BACKGROUND_COLOR.Water;
            case Electric:
                return BACKGROUND_COLOR.Electric;
            case Grass:
                return BACKGROUND_COLOR.Grass;
            case Ice:
                return BACKGROUND_COLOR.Ice;
            case Fighting:
                return BACKGROUND_COLOR.Fighting;
            case Poison:
                return BACKGROUND_COLOR.Poison;
            case Ground:
                return BACKGROUND_COLOR.Ground;
            case Flying:
                return BACKGROUND_COLOR.Flying;
            case Psychic:
                return BACKGROUND_COLOR.Psychic;
            case Bug:
                return BACKGROUND_COLOR.Bug;
            case Rock:
                return BACKGROUND_COLOR.Rock;
            case Ghost:
                return BACKGROUND_COLOR.Ghost;
            case Dragon:
                return BACKGROUND_COLOR.Dragon;
            case Dark:
                return BACKGROUND_COLOR.Dark;
            case Steel:
                return BACKGROUND_COLOR.Steel;
            case Fairy:
                return BACKGROUND_COLOR.Fairy;
            default:
                return BACKGROUND_COLOR.Unknown;
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