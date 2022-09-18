package fr.cpe.wolodiayannis.pokemongeo.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class ItemInventoryUnitTest {

    @Test
    public void testInventoryAddItem_shouldAddItemToTheInventory() {
        ItemsInventory itemsInventory = new ItemsInventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        itemsInventory.addItem(item, 1);
        System.out.println(itemsInventory.getItems());
        assertEquals(1, itemsInventory.getItems().size());
        assertEquals(2, itemsInventory.getItemQuantity(item));
    }

    @Test
    public void testInventoryRemoveItem_shouldDecrementAndRemoveItemFromTheInventory() {
        ItemsInventory itemsInventory = new ItemsInventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        itemsInventory.addItem(item, 1);
        itemsInventory.removeItem(item, 1);
        assertEquals(1, itemsInventory.getItems().size());
        assertEquals(1, itemsInventory.getItemQuantity(item));
        itemsInventory.removeItem(item, 1);
        assertEquals(0, itemsInventory.getItems().size());
    }

    @Test
    public void testInventoryAddItemTwice_shouldIncrementItemQuantity() {
        ItemsInventory itemsInventory = new ItemsInventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        itemsInventory.addItem(item, 1);
        itemsInventory.addItem(item, 1);
        assertEquals(1, itemsInventory.getItems().size());
        assertEquals(3, itemsInventory.getItemQuantity(item));
    }

    @Test
    public void testInventoryFilled_shouldThrowRuntimeException() {
        ItemsInventory itemsInventory = new ItemsInventory();
        Item item = null;
        // ADD 50 ITEMS with random names
        for (int i = 0; i < itemsInventory.getMaxItems(); i++) {
            item = Item.CREATE("Potion" + i, "Heal 20 HP", 0);
            itemsInventory.addItem(item, 1);
        }
        // ADD 1 ITEM
        item = Item.CREATE("Potion", "Heal 20 HP", 0);
        Item finalItem = item;
        assertThrows(RuntimeException.class, () -> itemsInventory.addItem(finalItem, 1));
    }

    @Test
    public void testInventoryRemoveItemNotInInventory_shouldThrowRuntimeException() {
        ItemsInventory itemsInventory = new ItemsInventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        assertThrows(RuntimeException.class, () -> itemsInventory.removeItem(item, 1));
    }

    @Test
    public void testInventoryRemoveItemQuantityTooHigh_shouldThrowRuntimeException() {
        ItemsInventory itemsInventory = new ItemsInventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        itemsInventory.addItem(item, 1);
        assertThrows(RuntimeException.class, () -> itemsInventory.removeItem(item, 200));
    }

    @Test
    public void testInventoryGetItemAtPosition_shouldReturnItem() {
        ItemsInventory itemsInventory = new ItemsInventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        itemsInventory.addItem(item, 1);
        assertEquals(item, itemsInventory.getItem(0));
    }

    @Test
    public void testInventoryGetItemQuantityOfUnexistingItem_shouldThrowRuntimeException() {
        ItemsInventory itemsInventory = new ItemsInventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        assertThrows(RuntimeException.class, () -> itemsInventory.getItemQuantity(item));
    }

    @Test
    public void testInventoryGetItemAtInvalidPosition_shouldThrowInternalError() {
        ItemsInventory itemsInventory = new ItemsInventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        itemsInventory.addItem(item, 1);
        assertThrows(InternalError.class, () -> itemsInventory.getItem(200));
        assertThrows(InternalError.class, () -> itemsInventory.getItem(-10));
    }

    @Test
    public void testInventoryGetItemAtPositionWhereThereIsNoItem_shouldReturnNull() {
        ItemsInventory itemsInventory = new ItemsInventory();
        Item item = Item.CREATE("Potion", "Heal 20 HP", 0);
        itemsInventory.addItem(item, 1);
        assertNull(itemsInventory.getItem(1));
    }
}
