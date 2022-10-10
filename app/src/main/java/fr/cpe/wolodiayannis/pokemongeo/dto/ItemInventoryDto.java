package fr.cpe.wolodiayannis.pokemongeo.dto;

import android.provider.ContactsContract;

import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;

public class ItemInventoryDto {
    int user_id;
    int[] item_id;
    int[] quantity;

    public ItemInventoryDto(ItemInventory itemInventory) {

        user_id = Datastore.getInstance().getUser().getId();
        int size = Datastore.getInstance().getSize();
        item_id = new int[size];
        quantity = new int[size];

        // for each item in the inventory
        for (Item item : itemInventory.getItemIventoryList().keySet()) {
            item_id[item.getId()] = item.getId();
            // get the quantity of the item
            try {
                quantity[item.getId()] = Objects.requireNonNull(itemInventory.getItemIventoryList().get(item));
            } catch (NullPointerException e) {
                quantity[item.getId()] = 0;
            }
        }
    }
}
