package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.ItemBallListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemBall;

@JsonAdapter(ItemBallListAdapter.class)
public class ItemBallList implements Serializable {

    /**
     * List of item ball.
     */
    private final List<ItemBall> itemBallList;

    /**
     * Constructor.
     *
     * @param itemBallList List of item ball.
     */
    public ItemBallList(List<ItemBall> itemBallList) {
        this.itemBallList = itemBallList;
    }

    /**
     * Get the item ball list.
     *
     * @return List of item ball.
     */
    public List<ItemBall> getItemBallList() {
        return itemBallList;
    }
}
