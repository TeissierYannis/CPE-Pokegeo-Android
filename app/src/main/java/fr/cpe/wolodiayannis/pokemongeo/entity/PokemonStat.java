package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.PokemonStatAdapter;

@JsonAdapter(PokemonStatAdapter.class)
public class PokemonStat {

    /**
     * Stat of the pokemon.
     */
    private Stat stat;

    /**
     * Base value of the stat.
     */
    private int baseStat;

    /**
     * constructor.
     * @param stat stat.
     * @param baseStat baseStat.
     */
    public PokemonStat(Stat stat, int baseStat) {
        this.stat = stat;
        this.baseStat = baseStat;
    }

    /**
     * Get stat.
     * @return stat
     */
    public Stat getStat() {
        return stat;
    }

    /**
     * Get base stat.
     * @return base stat.
     */
    public int getBaseStat() {
        return baseStat;
    }
}
