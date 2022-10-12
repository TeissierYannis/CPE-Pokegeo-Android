package fr.cpe.wolodiayannis.pokemongeo.listeners;

import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;

public interface InventoryUseInterface {
    void onItemInventorySwitch(Item item, CaughtPokemon caughtPokemon);
}
