package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Enum.BACKGROUND_COLOR;
import fr.cpe.wolodiayannis.pokemongeo.datas.EnumList;

public class Pokemon {

    private final int id;
    private final String name;
    private final int weight;
    private final int height;
    private final int backgroundColor;
    private final List<Type> types;
    private final List<Stat> stats;
    private final List<Ability> abilities;
    private final String description;
    private final int generation;

    /**
     * Default constructor
     * create a defaut pokemon
     */
    public Pokemon() {
        this.id = 0;
        this.name = "";
        this.weight = 0;
        this.height = 0;
        this.backgroundColor = BACKGROUND_COLOR.Unknown;
        this.types = new ArrayList<>();
        this.stats = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.description = "";
        this.generation = 0;

    }

    /**
     * Constructor
     * create a pokemon with all parameters
     * @param id the id of the pokemon
     * @param name the name of the pokemon
     * @param weight the weight of the pokemon
     * @param height the height of the pokemon
     */
    public Pokemon(int id, String name, int weight, int height) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;

        this.backgroundColor = generateColor();
        this.types = getTypesFromEnumList();
        this.stats = getStatsFromEnumList();
        this.abilities = getAbilitiesFromEnumList();
        this.description = getDescriptionFromEnumList();
        this.generation = getGenerationFromEnumList();
    }

    /**
     * Get the id of the pokemon
     * @return the id of the pokemon
     */
    public int getId() {
        return id;
    }

    /**
     * Get the name of the pokemon
     * @return the name of the pokemon
     */
    public String getName() {
        return name;
    }

    /**
     * Get the weight of the pokemon
     * @return the weight of the pokemon
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Get the height of the pokemon
     * @return the height of the pokemon
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the background color of the pokemon
     * @return the background color of the pokemon
     */
    public int getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Get the types of the pokemon
     * @return the types of the pokemon
     */
    public int getFrontResource() {
        return this.id;
    }

    /**
     * Get the types list of the pokemon
     * @return the types list of the pokemon
     */
    public List<Type> getTypes() { return types; }

    /**
     * Get the stats list of the pokemon from the enum list
     * @return the stats list of the pokemon
     */
    private List<Type> getTypesFromEnumList() {
        List<Type> types = new ArrayList<>();
        for (PokemonType pokemonType : EnumList.getPokemonsTypes()) {
            if (pokemonType.getPokemon_id() == this.id) {
                for (Type type : EnumList.getTypes()) {
                    if (type.getId() == pokemonType.getType_id()) {
                        types.add(type);
                    }
                }
            }
        }
        return types;
    }

    /**
     * Get the abilites list of the pokemon
     * @return the abilites list of the pokemon
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     * Get the abilites list of the pokemon from the enum list
     * @return the abilites list of the pokemon
     */
    public List<Ability> getAbilitiesFromEnumList() {
        List<Ability> abilities = new ArrayList<>();
        for (PokemonAbilities pokemonAbilities : EnumList.getPokemonsAbilities()) {
            if (pokemonAbilities.getPokemon_id() == this.id) {
                for (Ability ability : EnumList.getAbilities()) {
                    if (ability.getId() == pokemonAbilities.getAbility_id()) {
                        abilities.add(ability);
                    }
                }
            }
        }
        return abilities;
    }

    /**
     * Get the stats list of the pokemon
     * @return the stats list of the pokemon
     */
    public List<Stat> getStats() {
        return stats;
    }

    /**
     * Get the stats list of the pokemon from the enum list
     * @return the stats list of the pokemon
     */
    public List<Stat> getStatsFromEnumList() {
        List<Stat> stats = new ArrayList<>();
        for (PokemonStats pokemonStat : EnumList.getPokemonsStats()) {
            if (pokemonStat.getPokemon_id() == this.id) {
                for (Stat stat : EnumList.getStats()) {
                    if (stat.getId() == pokemonStat.getStat_id()) {
                        stats.add(stat);
                    }
                }
            }
        }
        return stats;
    }

    /**
     * Get the description of the pokemon
     * @return the description of the pokemon
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the description of the pokemon from the enum list
     * @return the description of the pokemon
     */
    public String getDescriptionFromEnumList() {
        for (PokemonDescription pokemonDescription : EnumList.getPokemonsDescription()) {
            if (pokemonDescription.getPokemon_id() == this.id) {
                return pokemonDescription.getDescription();
            }
        }
        return "";
    }

    /**
     * Get the generation of the pokemon
     * @return the generation of the pokemon
     */
    public int getGeneration() {
        return generation;
    }

    /**
     * Get the generation of the pokemon from the enum list
     * @return the generation of the pokemon
     */
    public int getGenerationFromEnumList() {
        for (PokemonGeneration pokemonGeneration : EnumList.getPokemonsGeneration()) {
            if (pokemonGeneration.getPokemon_id() == this.id) {
                return pokemonGeneration.getGeneration_id();
            }
        }
        return 0;
    }

    /**
     * Generate bg color in fact of type one.
     * @return color int.
     */
    public int generateColor() {
        switch (this.types.get(0).getName()) {
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
}