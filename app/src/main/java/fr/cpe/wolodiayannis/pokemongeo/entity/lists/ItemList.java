package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import java.io.Serializable;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemBall;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemPotion;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemRevive;

public class ItemList implements Serializable {

    /**
     * List of pokeball
     */
    private List<ItemBall> pokeballList;

    /**
     * List of potion
     */
    private List<ItemPotion> potionList;

    /**
     * List of revive
     */
    private List<ItemRevive> reviveList;


    /**
     * ItemList constructor by default.
     */
    public ItemList() {
        this.pokeballList = null;
        this.potionList = null;
        this.reviveList = null;
    }

    /**
     * ItemList constructor.
     *
     * @param pokeballList List of pokeball.
     * @param potionList   List of potion.
     * @param reviveList   List of revive.
     */
    public ItemList(List<ItemBall> pokeballList, List<ItemPotion> potionList, List<ItemRevive> reviveList) {
        this.pokeballList = pokeballList;
        this.potionList = potionList;
        this.reviveList = reviveList;
    }

    /**
     * Get list of pokeball.
     *
     * @return List of pokeball.
     */
    public List<ItemBall> getPokeballList() {
        return pokeballList;
    }

    /**
     * Set list of pokeball.
     *
     * @param pokeballList List of pokeball.
     */
    public void setPokeballList(List<ItemBall> pokeballList) {
        this.pokeballList = pokeballList;
    }

    /**
     * Get list of potion.
     *
     * @return List of potion.
     */
    public List<ItemPotion> getPotionList() {
        return potionList;
    }

    /**
     * Set list of potion.
     *
     * @param potionList List of potion.
     */
    public void setPotionList(List<ItemPotion> potionList) {
        this.potionList = potionList;
    }

    /**
     * Get list of revive.
     *
     * @return List of revive.
     */
    public List<ItemRevive> getReviveList() {
        return reviveList;
    }

    /**
     * Set list of revive.
     *
     * @param reviveList List of revive.
     */
    public void setReviveList(List<ItemRevive> reviveList) {
        this.reviveList = reviveList;
    }

    /**
     * Get item by id
     *
     * @param id item id
     * @return item corresponding to the id
     */
    public Item getItemById(int id) {
        // check the id for each list
        for (ItemBall itemBall : pokeballList) {
            if (itemBall.getId() == id) {
                return itemBall;
            }
        }
        for (ItemPotion itemPotion : potionList) {
            if (itemPotion.getId() == id) {
                return itemPotion;
            }
        }
        for (ItemRevive itemRevive : reviveList) {
            if (itemRevive.getId() == id) {
                return itemRevive;
            }
        }
        return null;
    }
}
