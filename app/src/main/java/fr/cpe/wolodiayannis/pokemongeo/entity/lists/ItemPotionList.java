package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.ItemPotionListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemPotion;

@JsonAdapter(ItemPotionListAdapter.class)
public class ItemPotionList implements Serializable {

    /**
     * List of item potion.
     */
    private final List<ItemPotion> itemPotionList;

    /**
     * Constructor.
     *
     * @param itemPotionList List of item potion.
     */
    public ItemPotionList(List<ItemPotion> itemPotionList) {
        this.itemPotionList = itemPotionList;
    }

    /**
     * Get the item potion list.
     *
     * @return List of item potion.
     */
    public List<ItemPotion> getItemPotionList() {
        return itemPotionList;
    }
}
