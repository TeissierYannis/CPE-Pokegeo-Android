package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import java.io.Serializable;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemBall;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemPotion;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemRevive;

public class ItemList implements Serializable {

    /**
     * List of pokeball
     */
    private final List<ItemBall> pokeballList;

    /**
     * List of potion
     */
    private final List<ItemPotion> potionList;

    /**
     * List of revive
     */
    private final List<ItemRevive> reviveList;


    /**
     * ItemList constructor.
     * @param pokeballList List of pokeball.
     * @param potionList List of potion.
     * @param reviveList List of revive.
     */
    public ItemList(List<ItemBall> pokeballList, List<ItemPotion> potionList, List<ItemRevive> reviveList) {
        this.pokeballList = pokeballList;
        this.potionList = potionList;
        this.reviveList = reviveList;
    }

    /**
     * Get list of pokeball.
     * @return List of pokeball.
     */
    public List<ItemBall> getPokeballList() {
        return pokeballList;
    }

    /**
     * Get list of potion.
     * @return List of potion.
     */
    public List<ItemPotion> getPotionList() {
        return potionList;
    }

    /**
     * Get list of revive.
     * @return List of revive.
     */
    public List<ItemRevive> getReviveList() {
        return reviveList;
    }


}
