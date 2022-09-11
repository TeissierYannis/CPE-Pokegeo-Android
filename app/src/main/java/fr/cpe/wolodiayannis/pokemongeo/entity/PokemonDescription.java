package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pokemon_description")
public class PokemonDescription {
    @DatabaseField(generatedId = true)
    private final int pokemon_id;
    @DatabaseField
    private final String description;

    public PokemonDescription(int pokemon_id, String description) {
        this.pokemon_id = pokemon_id;
        this.description = description;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public String getDescription() {
        return description;
    }
}
