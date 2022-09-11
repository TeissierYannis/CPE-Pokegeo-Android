package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.util.List;

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