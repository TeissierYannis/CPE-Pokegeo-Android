package fr.cpe.wolodiayannis.pokemongeo.entity;

import org.junit.Test;

public class CaughtInventoryUnitTest {

    @Test
    public void testAddCaughtPokemon_shouldAddPokemonToInventory() {
        CaughtInventory inventory = new CaughtInventory();
        Pokemon pokemon = Pokemon.CREATE(1, "Bulbasaur", "Seed", null, 0.7f, 6.9f, null, null, null, "", 1, 0);

    }
}
