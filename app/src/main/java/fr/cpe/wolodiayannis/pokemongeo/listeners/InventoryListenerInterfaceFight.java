package fr.cpe.wolodiayannis.pokemongeo.listeners;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;

/**
 * Interface for the item inventory listener in fight frag.
 */
public interface InventoryListenerInterfaceFight {
    /**
     * On select item.
     *
     * @param item item
     */
    void onItemSelectedFight(Item item);

}
