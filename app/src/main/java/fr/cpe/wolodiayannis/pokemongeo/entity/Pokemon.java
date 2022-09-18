package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;
import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.PokemonAdapter;

/**
 * Pokemon class.
 */
@JsonAdapter(PokemonAdapter.class)
public class Pokemon {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    @SerializedName("description")
    private String description;

    @SerializedName("generation_id")
    private int generationId;

    @SerializedName("evolution_chain_id")
    private int evolutionChainId;

    public Pokemon(int id, String name, int height, int weight, String description, int generationId, int evolutionChainId) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.description = description;
        this.generationId = generationId;
        this.evolutionChainId = evolutionChainId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    public int getGenerationId() {
        return generationId;
    }

    public int getEvolutionChainId() {
        return evolutionChainId;
    }
}