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
    private final List<PokemonType> PokemonTypes;

    public Pokemon() {
        this.id = 0;
        this.name = "";
        this.weight = 0;
        this.height = 0;
        this.backgroundColor = BACKGROUND_COLOR.Unknown;
        this.PokemonTypes = new ArrayList<>();
    }

    public Pokemon(int id, String name, int weight, int height) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;

        this.PokemonTypes = new ArrayList<>();
        for (PokemonType pokemonType : EnumList.getPokemonsTypes()) {
            if (pokemonType.getPokemon_id() == id) {
                this.PokemonTypes.add(pokemonType);
            }
        }
        this.backgroundColor = generateColor();
    }

    public List<Type> getTypes() {
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

    public List<Ability> getAbilities() {
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

    public List<Stat> getStats() {
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

    public String getDescription() {
        for (PokemonDescription pokemonDescription : EnumList.getPokemonsDescription()) {
            if (pokemonDescription.getPokemon_id() == this.id) {
                return pokemonDescription.getDescription();
            }
        }
        return "";
    }

    public int getGeneration() {
        for (PokemonGeneration pokemonGeneration : EnumList.getPokemonsGeneration()) {
            if (pokemonGeneration.getPokemon_id() == this.id) {
                return pokemonGeneration.getGeneration_id();
            }
        }
        return 0;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getFrontResource() {
        return this.id;
    }

    /**
     * Generate bg color in fact of type one.
     * @return color int.
     */
    public int generateColor() {
        switch (this.PokemonTypes.get(0).getType_id()) {
            case 1 :
                return BACKGROUND_COLOR.Normal;
            case 2:
                return BACKGROUND_COLOR.Fighting;
            case 3:
                return BACKGROUND_COLOR.Flying;
            case 4:
                return BACKGROUND_COLOR.Poison;
            case 5:
                return BACKGROUND_COLOR.Ground;
            case 6:
                return BACKGROUND_COLOR.Rock;
            case 7:
                return BACKGROUND_COLOR.Bug;
            case 8:
                return BACKGROUND_COLOR.Ghost;
            case 9:
                return BACKGROUND_COLOR.Steel;
            case 10:
                return BACKGROUND_COLOR.Fire;
            case 11:
                return BACKGROUND_COLOR.Water;
            case 12:
                return BACKGROUND_COLOR.Grass;
            case 13:
                return BACKGROUND_COLOR.Electric;
            case 14:
                return BACKGROUND_COLOR.Psychic;
            case 15:
                return BACKGROUND_COLOR.Ice;
            case 16:
                return BACKGROUND_COLOR.Dragon;
            case 17:
                return BACKGROUND_COLOR.Dark;
            case 18:
                return BACKGROUND_COLOR.Fairy;
            default:
                return BACKGROUND_COLOR.Unknown;
        }
    }
}