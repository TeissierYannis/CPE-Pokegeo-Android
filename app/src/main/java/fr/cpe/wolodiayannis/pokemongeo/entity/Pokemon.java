package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Enum.BACKGROUND_COLOR;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;

/**
 * Pokemon class.
 */
public class Pokemon {
    /**
     * Pokemon's ID.
     */
    private final int ID;
    /**
     * Pokemon's name.
     */
    private final String name;
    /**
     * Pokemon's species.
     */
    private final String species;
    /**
     * Pokemon's type.
     */
    private final List<POKEMON_TYPE> types;
    /**
     * Pokemon's height.
     */
    private final float height;
/**
     * Pokemon's weight.
     */
    private final float weight;
    /**
     * Pokemon's abilities.
     */
    private final List<POKEMON_ABILITIES> abilities;
    /**
     * Pokemon's stats.
     */
    private final Stats stats;
    /**
     * Pokemon's evolution.
     */
    private final List<Pokemon> evolutions;
    /**
     * Pokemon's description.
     */
    private final String description;
    /**
     * Pokemon gen.
     */
    private final int gen;
    /**
     * Pokemon's image id.
     */
    private final int imgID;
    /**
     * Pokemon bg color.
     */
    private final int color;
    /**
     * Pokemon quantity.
     * TODO ?
     */
    private int quantity;

    /**
     * Pokemon constructor.
     */
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

    /**
     * Pokemon constructor.
     * @param ID Pokemon's ID.
     * @param name Pokemon's name.
     * @param species Pokemon's species.
     * @param types Pokemon's type.
     * @param height Pokemon's height.
     * @param weight Pokemon's weight.
     * @param abilities Pokemon's abilities.
     * @param stats Pokemon's stats.
     * @param evolutions Pokemon's evolution.
     * @param description Pokemon's description.
     * @param gen Pokemon gen.
     * @param imgID Pokemon's image id.
     */
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

    /**
     * Pokemon factory.
     * @param ID Pokemon's ID.
     * @param name Pokemon's name.
     * @param species Pokemon's species.
     * @param types Pokemon's type.
     * @param height Pokemon's height.
     * @param weight Pokemon's weight.
     * @param abilities Pokemon's abilities.
     * @param stats Pokemon's stats.
     * @param evolutions Pokemon's evolution.
     * @param description Pokemon's description.
     * @param gen Pokemon gen.
     * @param imgID Pokemon's image id.
     * @return Pokemon.
     */
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

    /**
     * Generate bg color in fact of type one.
     * @return color int.
     */
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

    /**
     * Get Pokemon's ID.
     * @return Pokemon's ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Get Pokemon's name.
     * @return Pokemon's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get Pokemon's species.
     * @return Pokemon's species.
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Get Pokemon's type.
     * @return Pokemon's type.
     */
    public List<POKEMON_TYPE> getTypes() {
        return types;
    }

    /**
     * Get Pokemon's height.
     * @return Pokemon's height.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Get Pokemon's weight.
     * @return Pokemon's weight.
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Get Pokemon's abilities.
     * @return Pokemon's abilities.
     */
    public List<POKEMON_ABILITIES> getAbilities() {
        return abilities;
    }

    /**
     * Get Pokemon's stats.
     * @return Pokemon's stats.
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Get Pokemon's evolution.
     * @return Pokemon's evolution.
     */
    public List<Pokemon> getEvolutions() {
        return evolutions;
    }

    /**
     * Get Pokemon's description.
     * @return Pokemon's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get Pokemon's gen.
     * @return Pokemon's gen.
     */
    public int getGen() {
        return gen;
    }

    /**
     * Get Pokemon's image id.
     * @return Pokemon's image id.
     */
    public int getFrontResource() {
        return this.imgID;
    }

    /**
     * Get Pokemon's bg color.
     * @return Pokemon's bg color.
     */
    public int getColor() {
        return this.color;
    }

    // TODO ?
    public int getQuantity() {
        return this.quantity;
    }

    // TODO ?
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    // TODO ?
    public void removeQuantity(int quantity) {

        if (this.quantity - quantity < 0) {
            throw new RuntimeException("The quantity is superior to the quantity in the inventory");
        } else {
            this.quantity -= quantity;
        }
    }
}