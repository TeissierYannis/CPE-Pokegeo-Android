package fr.cpe.wolodiayannis.pokemongeo.entity.item;

import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;

public class ItemPotion extends Item {

    /**
     * Potion bonus amount.
     */
    private final int bonus;

    /**
     * Stat to apply the bonus.
     */
    private Stat stat;

    /**
     * Item constructor.
     *
     * @param id    Item id.
     * @param name  Item name.
     * @param price Item price.
     * @param stat_id Stat id.
     * @param bonus Potion bonus amount.
     */
    public ItemPotion(int id, String name, int price, int stat_id, int bonus) {
        super(id, name, price);
        this.bonus = bonus;

        Stat stat = null;
    }

    /**
     * Get potion bonus amount.
     * @return Potion bonus amount.
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * Get stat to apply the bonus.
     * @return Stat to apply the bonus.
     */
    public Stat getStat() {
        return stat;
    }

    /**
     * Set stat to apply the bonus.
     * @param stat Stat to apply the bonus.
     */
    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
