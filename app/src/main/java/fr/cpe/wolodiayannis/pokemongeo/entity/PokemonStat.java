package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.io.Serializable;

public class PokemonStat implements Serializable {

    /**
     * Stat of the pokemon.
     */
    private final Stat stat;

    /**
     * Base value of the stat.
     */
    private final int baseStat;

    /**
     * constructor.
     *
     * @param stat     stat.
     * @param baseStat baseStat.
     */
    public PokemonStat(Stat stat, int baseStat) {
        this.stat = stat;
        this.baseStat = baseStat;
    }

    /**
     * Get stat.
     *
     * @return stat
     */
    public Stat getStat() {
        return stat;
    }

    /**
     * Get base stat.
     *
     * @return base stat.
     */
    public int getBaseStat() {
        return baseStat;
    }
}
