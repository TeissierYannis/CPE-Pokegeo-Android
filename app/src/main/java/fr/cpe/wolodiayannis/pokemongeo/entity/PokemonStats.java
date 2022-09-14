package fr.cpe.wolodiayannis.pokemongeo.entity;

public class PokemonStats {
    private final int pokemon_id;
    private final int stat_id;
    private final int base_stat;

    public PokemonStats(int pokemon_id, int stat_id, int base_stat) {
        this.pokemon_id = pokemon_id;
        this.stat_id = stat_id;
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

    public void fetchPokemonStats() {
        // TODO : fetch pokemon stats from database
    }
}
