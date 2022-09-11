package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pokemon_type")
public class PokemonTypes {
    @DatabaseField(generatedId = true)
    private final int pokemon_id;
    @DatabaseField(generatedId = true)
    private final int type_id;

    public PokemonTypes(int pokemon_id, int type_id) {
        this.pokemon_id = pokemon_id;
        this.type_id = type_id;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public int getType_id() {
        return type_id;
    }
}
