package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pokemon_stats")
public class PokemonStats {
    @DatabaseField(generatedId = true)
    private final int pokemon_id;
    @DatabaseField(generatedId = true)
    private final int stat_id;
    @DatabaseField
    private final int base_stat;

    public PokemonStats(int pokemon_id, int stats_id, int base_stat) {
        this.pokemon_id = pokemon_id;
        this.stat_id = stats_id;
        this.base_stat = base_stat;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public int getStat_id() {
        return stat_id;
    }

    public int getBase_stat() {
        return base_stat;
    }
}
