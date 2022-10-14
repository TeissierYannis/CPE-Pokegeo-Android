package fr.cpe.wolodiayannis.pokemongeo.dto;

import java.io.Serializable;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;

public class ItemInventoryDto implements Serializable {
    /**
     * user id.
     */
    int user_id;
    /**
     * Quantity of items.
     */
    int[] quantity;

    /**
     * Constructor.
     *
     * @param itemInventory item inventory
     */
    public ItemInventoryDto(ItemInventory itemInventory) {

        user_id = Datastore.getInstance().getUser().getId();
        int size = Datastore.getInstance().getSize();
        quantity = new int[size];

        // for each item in the inventory
        for (Item item : itemInventory.getItemIventoryList().keySet()) {
            // get the quantity of the item
            try {
                quantity[item.getId()] = Objects.requireNonNull(itemInventory.getItemIventoryList().get(item));
            } catch (NullPointerException e) {
                quantity[item.getId()] = 0;
            }
        }
    }
}
