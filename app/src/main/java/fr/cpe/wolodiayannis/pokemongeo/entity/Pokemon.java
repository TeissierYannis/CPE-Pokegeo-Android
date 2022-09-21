package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import fr.cpe.wolodiayannis.pokemongeo.Enum.BACKGROUND_COLOR;
import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.PokemonAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.AbilityList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.StatList;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.TypeList;

/**
 * Pokemon class.
 */
@JsonAdapter(PokemonAdapter.class)
public class Pokemon implements Serializable {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;
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
     * List of abilities.
     */
    private final AbilityList abilityList;

    /**
     * List of types.
     */
    private final TypeList typeList;

    /**
     * List of stats.
     */
    private final StatList statList;

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

        this.setImgID(-1);
        // TODO to redefined
        this.color = BACKGROUND_COLOR.Unknown;

        this.abilityList = new AbilityList(new ArrayList<>());
        this.typeList = new TypeList(new ArrayList<>());
        this.statList = new StatList(new ArrayList<>());
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
     * Get Pokemon background color.
     * @return int Pokemon background color.
     */
    public int getBackgroundColor() {
        return color;
    }

    /**
     * Generate bg color in fact of type one.
     * @return color int.
    */
    public int generateColor() {
        switch (this.typeList.getTypeList().get(0).getName()) {
            case "normal" :
                return BACKGROUND_COLOR.Normal;
            case "fighting" :
                return BACKGROUND_COLOR.Fighting;
            case "flying" :
                return BACKGROUND_COLOR.Flying;
            case "poison" :
                return BACKGROUND_COLOR.Poison;
            case "ground" :
                return BACKGROUND_COLOR.Ground;
            case "rock":
                return BACKGROUND_COLOR.Rock;
            case "bug":
                return BACKGROUND_COLOR.Bug;
            case "ghost":
                return BACKGROUND_COLOR.Ghost;
            case "steel":
                return BACKGROUND_COLOR.Steel;
            case "fire":
                return BACKGROUND_COLOR.Fire;
            case "water":
                return BACKGROUND_COLOR.Water;
            case "grass":
                return BACKGROUND_COLOR.Grass;
            case "electric":
                return BACKGROUND_COLOR.Electric;
            case "psychic":
                return BACKGROUND_COLOR.Psychic;
            case "ice":
                return BACKGROUND_COLOR.Ice;
            case "dragon":
                return BACKGROUND_COLOR.Dragon;
            case "dark":
                return BACKGROUND_COLOR.Dark;
            case "fairy":
                return BACKGROUND_COLOR.Fairy;
            default:
                return BACKGROUND_COLOR.Unknown;
        }
    }

    /**
     * Get abilities.
     * @return abilities
     */
    public AbilityList getAbilities() {
        return this.abilityList;
    }

    /**
     * Get types list.
     * @return types list
     */
    public TypeList getTypes() {
        return this.typeList;
    }

    /**
     * Get stats list.
     * @return stats list
     */
    public StatList getStats() {
        return this.statList;
    }

    /**
     * Get abilities list.
     * @return abilities list
     */
    public AbilityList getAbility() { return abilityList;}

    /**
     * Get Pokemon's image id.
     * @return Pokemon's image id.
     */
    public int getFrontResource() {
        return this.imgID;
    }
}