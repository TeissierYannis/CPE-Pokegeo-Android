package fr.cpe.wolodiayannis.pokemongeo.entity.item;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

public class ItemRevive extends Item {

    /**
     * Revive hp amount giving.
     */
    private final int hp_amount;

    /**
     * Revive constructor.
     *
     * @param id    Item id.
     * @param name  Item name.
     * @param price Item price.
     * @param hp_amount Revive hp amount giving.
     */
    public ItemRevive(int id, String name, int price, int hp_amount) {
        super(id, name, price);
        this.hp_amount = hp_amount;
    }

    /**
     * Get revive hp amount giving.
     * @return Revive hp amount giving.
     */
    public int getHpAmount() {
        return hp_amount;
    }

    /**
     * Get the exact amount of hp given by the revive for the pokemon.
     * @param pokemon Pokemon to revive.
     * @return int Exact amount of hp given by the revive for the pokemon.
     */
    public int getExactHpToHeal(Pokemon pokemon) {
        return (int) Math.round((pokemon.getHp() * hp_amount) / 100.0);
    }
}
