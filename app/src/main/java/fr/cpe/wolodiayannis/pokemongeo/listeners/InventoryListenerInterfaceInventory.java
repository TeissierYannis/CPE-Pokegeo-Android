package fr.cpe.wolodiayannis.pokemongeo.listeners;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;

/**
 * Interface for the item inventory listener in inventory frag.
 */
public interface InventoryListenerInterfaceInventory {
    /**
     * On select item.
     *
     * @param item item
     */
    void onItemSelectedInv(Item item);
}
