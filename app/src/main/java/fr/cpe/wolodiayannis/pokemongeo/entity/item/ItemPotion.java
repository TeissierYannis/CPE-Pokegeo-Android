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
    private final Stat stat;

    /**
     * Item constructor.
     *
     * @param id    Item id.
     * @param name  Item name.
     * @param price Item price.
     * @param bonus Potion bonus amount.
     * @param stat Stat to apply the bonus.
     */
    public ItemPotion(int id, String name, int price, int bonus, Stat stat) {
        super(id, name, price);
        this.bonus = bonus;
        this.stat = stat;
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
}
