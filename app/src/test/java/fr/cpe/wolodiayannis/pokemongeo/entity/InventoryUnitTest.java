package fr.cpe.wolodiayannis.pokemongeo.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class InventoryUnitTest {

    @Test
    public void testInventoryAddItem_shouldAddItemToTheInventory() {
        Inventory inventory = new Inventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        inventory.addItem(item, 1);
        System.out.println(inventory.getItems());
        assertEquals(1, inventory.getItems().size());
        assertEquals(2, inventory.getItemQuantity(item));
    }

    @Test
    public void testInventoryRemoveItem_shouldDecrementAndRemoveItemFromTheInventory() {
        Inventory inventory = new Inventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        inventory.addItem(item, 1);
        inventory.removeItem(item, 1);
        assertEquals(1, inventory.getItems().size());
        assertEquals(1, inventory.getItemQuantity(item));
        inventory.removeItem(item, 1);
        assertEquals(0, inventory.getItems().size());
    }

    @Test
    public void testInventoryAddItemTwice_shouldIncrementItemQuantity() {
        Inventory inventory = new Inventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        inventory.addItem(item, 1);
        inventory.addItem(item, 1);
        assertEquals(1, inventory.getItems().size());
        assertEquals(3, inventory.getItemQuantity(item));
    }

    @Test
    public void testInventoryFilled_shouldThrowRuntimeException() {
        Inventory inventory = new Inventory();
        Item item = null;
        // ADD 50 ITEMS with random names
        for (int i = 0; i < inventory.getMaxItems(); i++) {
            item = Item.CREATE("Potion" + i, "Heal 20 HP", 0);
            inventory.addItem(item, 1);
        }
        // ADD 1 ITEM
        item = Item.CREATE("Potion", "Heal 20 HP", 0);
        Item finalItem = item;
        assertThrows(RuntimeException.class, () -> inventory.addItem(finalItem, 1));
    }

    @Test
    public void testInventoryRemoveItemNotInInventory_shouldThrowRuntimeException() {
        Inventory inventory = new Inventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        assertThrows(RuntimeException.class, () -> inventory.removeItem(item, 1));
    }

    @Test
    public void testInventoryRemoveItemQuantityTooHigh_shouldThrowRuntimeException() {
        Inventory inventory = new Inventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        inventory.addItem(item, 1);
        assertThrows(RuntimeException.class, () -> inventory.removeItem(item, 200));
    }

    @Test
    public void testInventoryGetItemAtPosition_shouldReturnItem() {
        Inventory inventory = new Inventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        inventory.addItem(item, 1);
        assertEquals(item, inventory.getItem(0));
    }

    @Test
    public void testInventoryGetItemQuantityOfUnexistingItem_shouldThrowRuntimeException() {
        Inventory inventory = new Inventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        assertThrows(RuntimeException.class, () -> inventory.getItemQuantity(item));
    }

    @Test
    public void testInventoryGetItemAtInvalidPosition_shouldThrowInternalError() {
        Inventory inventory = new Inventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        inventory.addItem(item, 1);
        assertThrows(InternalError.class, () -> inventory.getItem(200));
        assertThrows(InternalError.class, () -> inventory.getItem(-10));
    }

    @Test
    public void testInventoryGetItemAtPositionWhereThereIsNoItem_shouldReturnNull() {
        Inventory inventory = new Inventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        inventory.addItem(item, 1);
        assertNull(inventory.getItem(1));
    }
}
