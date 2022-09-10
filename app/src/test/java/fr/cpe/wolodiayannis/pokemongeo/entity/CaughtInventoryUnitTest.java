package fr.cpe.wolodiayannis.pokemongeo.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;

public class CaughtInventoryUnitTest {


    public Pokemon createbulba() {
        List<POKEMON_TYPE> types = new ArrayList<>();
        types.add(POKEMON_TYPE.Grass);
        types.add(POKEMON_TYPE.Poison);
        List<POKEMON_ABILITIES> abilities = new ArrayList<>();
        abilities.add(POKEMON_ABILITIES.Overgrow);
        abilities.add(POKEMON_ABILITIES.Chlorophyll);
        Stats stats = new Stats(45, 49, 49, 65, 65, 45);
        return Pokemon.CREATE(1, "Bulbasaur", "Seed Pokemon",types , 0.7f, 6.9f, abilities, stats, null, "desc", 1, 0);
    }

    @Test
    public void testCaughtAddPokemon_shouldAddPokemonToTheInventory() {
        CaughtInventory caughtInventory = new CaughtInventory();
        Pokemon pokemon = createbulba();
        caughtInventory.addCaughtPokemon(pokemon, 1);
        System.out.println(caughtInventory.getCaughtPokemon());
        assertEquals(1, caughtInventory.getCaughtPokemon().size());
        assertEquals(1, caughtInventory.getCaughtPokemonQuantity(pokemon));
    }

    @Test
    public void testCaughtAddPokemon_shouldAddFewPokemonToTheInventory() {
        CaughtInventory caughtInventory = new CaughtInventory();
        Pokemon pokemon = createbulba();
        caughtInventory.addCaughtPokemon(pokemon, 3);
        System.out.println(caughtInventory.getCaughtPokemon());
        assertEquals(1, caughtInventory.getCaughtPokemon().size());
        assertEquals(3, caughtInventory.getCaughtPokemonQuantity(pokemon));
    }

    @Test
    public void testInventoryRemoveCaught_shouldDecrementAndRemoveCaughtFromTheCaughtInventory() {
        CaughtInventory caughtInventory = new CaughtInventory();
        Pokemon pokemon = createbulba();
        caughtInventory.addCaughtPokemon(pokemon, 2);
        caughtInventory.removeCaughtPokemon(pokemon, 1);
        assertEquals(1, caughtInventory.getCaughtPokemon().size());
        assertEquals(1, caughtInventory.getCaughtPokemonQuantity(pokemon));
        caughtInventory.removeCaughtPokemon(pokemon, 1);
        assertEquals(0, caughtInventory.getCaughtPokemon().size());
    }

    @Test
    public void testCaughtAddCaughtTwice_shouldIncrementItemQuantity() {
        CaughtInventory caughtInventory = new CaughtInventory();
        Pokemon pokemon = createbulba();
        caughtInventory.addCaughtPokemon(pokemon, 1);
        caughtInventory.addCaughtPokemon(pokemon, 1);
        assertEquals(1, caughtInventory.getCaughtPokemon().size());
        assertEquals(2, caughtInventory.getCaughtPokemonQuantity(pokemon));
    }


    @Test
    public void testCaughtRemovePokemonNotInCaughtInventory_shouldThrowRuntimeException() {
        CaughtInventory caughtInventory = new CaughtInventory();
        Pokemon pokemon = createbulba();
        assertThrows(RuntimeException.class, () -> caughtInventory.removeCaughtPokemon(pokemon, 1));
    }

    @Test
    public void testCaughtInventoryRemoveCaughtQuantityTooHigh_shouldThrowRuntimeException() {
        CaughtInventory caughtInventory = new CaughtInventory();
        Pokemon pokemon = createbulba();
        caughtInventory.addCaughtPokemon(pokemon, 1);
        assertThrows(RuntimeException.class, () -> caughtInventory.removeCaughtPokemon(pokemon, 200));
    }

    @Test
    public void testCaughtInventoryGetCaughtAtPosition_shouldReturnCaught() {
        CaughtInventory caughtInventory = new CaughtInventory();
        Pokemon pokemon = createbulba();
        caughtInventory.addCaughtPokemon(pokemon, 1);
        assertEquals(pokemon, caughtInventory.getCaughtPokemon(0));
    }

    @Test
    public void testCaughtInventoryGetCaughtQuantityOfUnexistingPokemon_shouldThrowRuntimeException() {
        CaughtInventory caughtInventory = new CaughtInventory();
        Pokemon pokemon = createbulba();
        assertThrows(RuntimeException.class, () -> caughtInventory.getCaughtPokemonQuantity(pokemon));
    }

    @Test
    public void testCaughtInventoryGetPokemonAtInvalidPosition_shouldThrowInternalError() {
        CaughtInventory caughtInventory = new CaughtInventory();
        Pokemon pokemon = createbulba();
        caughtInventory.addCaughtPokemon(pokemon, 1);
        assertThrows(InternalError.class, () -> caughtInventory.getCaughtPokemon(200));
        assertThrows(InternalError.class, () -> caughtInventory.getCaughtPokemon(-10));
    }

    @Test
    public void testCaughtInventoryGetPokemonAtPositionWhereThereIsNoPokemon_shouldReturnNull() {
        CaughtInventory caughtInventory = new CaughtInventory();
        Pokemon pokemon = createbulba();
        caughtInventory.addCaughtPokemon(pokemon, 1);
        assertThrows(InternalError.class, () -> caughtInventory.getCaughtPokemon(1));
    }
}
