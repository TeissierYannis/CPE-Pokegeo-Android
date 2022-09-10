package fr.cpe.wolodiayannis.pokemongeo.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemUnitTest {

    @Test
    public void testItem() {
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        assertEquals("Potion", item.getName());
        assertEquals("Heal 20 HP", item.getDescription());
        assertEquals(1, item.getQuantity());
        assertEquals(0, item.getFrontResource());
    }
}
