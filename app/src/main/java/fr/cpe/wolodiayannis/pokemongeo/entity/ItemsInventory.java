package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.ItemsInventoryAdapter;

/**
 * Items inventory class.
 */
@JsonAdapter(ItemsInventoryAdapter.class)
public class ItemsInventory {

}
