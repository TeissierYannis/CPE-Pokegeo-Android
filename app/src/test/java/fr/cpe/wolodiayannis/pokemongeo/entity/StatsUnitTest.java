package fr.cpe.wolodiayannis.pokemongeo.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatsUnitTest {

    @Test
    public void testStats() {
        Stat stats = Stat.CREATE(1, 2, 3, 4, 5, 6);
        assertEquals(1, stats.getHp());
        assertEquals(2, stats.getAttack());
        assertEquals(3, stats.getDefense());
        assertEquals(4, stats.getSpecialAttack());
        assertEquals(5, stats.getSpecialDefense());
        assertEquals(6, stats.getSpeed());
        assertEquals(21, stats.getTotal());
    }
}
