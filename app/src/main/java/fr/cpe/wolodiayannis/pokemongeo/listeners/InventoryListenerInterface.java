package fr.cpe.wolodiayannis.pokemongeo.listeners;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;

/**
 * Interface for the item inventory listener
 */
public interface InventoryListenerInterface {
    /**
     * On select item.
     * @param item item
     */
    void onItemSelected(Item item);
}
