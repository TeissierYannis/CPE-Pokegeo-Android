package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.PokemonAdapter;

/**
 * Pokemon class.
 */
@JsonAdapter(PokemonAdapter.class)
public class Pokemon {
    /**
     * Pokemon ID.
     */
    @SerializedName("id")
    private final int id;

    /**
     * Pokemon name.
     */
    @SerializedName("name")
    private final String name;

    /**
     * Pokemon height.
     */
    @SerializedName("height")
    private final int height;

    /**
     * Pokemon weight.
     */
    @SerializedName("weight")
    private final int weight;

    /**
     * Pokemon description.
     */
    @SerializedName("description")
    private final String description;

    /**
     * Pokemon generation ID.
     */
    @SerializedName("generation_id")
    private final int generationId;

    /**
     * Pokemon evolution chain ID.
     */
    @SerializedName("evolution_chain_id")
    private final int evolutionChainId;

    /**
     * Pokemon's image id.
     */
    private int imgID;

    /**
     * Pokemon bg color.
     */
    private int color;

    /**
     * Pokemon constructor.
     * @param id Pokemon ID.
     * @param name Pokemon name.
     * @param height Pokemon height.
     * @param weight Pokemon weight.
     * @param description Pokemon description.
     * @param generationId Pokemon generation ID.
     * @param evolutionChainId Pokemon evolution chain ID.
     */
    public Pokemon(int id, String name, int height, int weight, String description, int generationId, int evolutionChainId) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.description = description;
        this.generationId = generationId;
        this.evolutionChainId = evolutionChainId;
    }

    /**
     * Pokemon factory.
     * @param id Pokemon ID.
     * @param name Pokemon name.
     * @param height Pokemon height.
     * @param weight Pokemon weight.
     * @param description Pokemon description.
     * @param generationId Pokemon generation ID.
     * @param evolutionChainId Pokemon evolution chain ID.
     * @return Pokemon instance.
     */
    public static Pokemon CREATE(int id, String name, int height, int weight, String description, int generationId, int evolutionChainId) {
        return new Pokemon(id, name, height, weight, description, generationId, evolutionChainId);
    }

    /**
     * Pokemon set image ID.
     * @param imgID Pokemon image ID.
     * @return Pokemon Pokemon instance.
     */
    public Pokemon setImgID(int imgID) {
        this.imgID = imgID;
        return this;
    }

    /**
     * Get Pokemon ID.
     * @return int Pokemon ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Get Pokemon name.
     * @return String Pokemon name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get Pokemon height.
     * @return int Pokemon height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get Pokemon weight.
     * @return int Pokemon weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Get Pokemon description.
     * @return String Pokemon description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get Pokemon generation ID.
     * @return int Pokemon generation ID.
     */
    public int getGenerationId() {
        return generationId;
    }

    /**
     * Get Pokemon evolution chain ID.
     * @return int Pokemon evolution chain ID.
     */
    public int getEvolutionChainId() {
        return evolutionChainId;
    }

    /**
     * Generate bg color in fact of type one.
     * @return color int.
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

    */

    /**
     * Get Pokemon's image id.
     * @return Pokemon's image id.
     */
    public int getFrontResource() {
        return this.imgID;
    }
}