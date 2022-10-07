package fr.cpe.wolodiayannis.pokemongeo.entity;

public class ItemRevive extends Item {

    /**
     * Revive hp amount giving.
     */
    private final int hp;

    /**
     * Revive constructor.
     *
     * @param id    Item id.
     * @param name  Item name.
     * @param price Item price.
     * @param hp    Revive hp amount giving.
     */
    public ItemRevive(int id, String name, int price, int hp) {
        super(id, name, price);
        this.hp = hp;
    }

    /**
     * Get revive hp amount giving.
     * @return Revive hp amount giving.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Get the exact amount of hp given by the revive for the pokemon.
     * @param pokemon Pokemon to revive.
     * @return int Exact amount of hp given by the revive for the pokemon.
     */
    public int getExactHp(Pokemon pokemon) {
        return (int) Math.round((pokemon.getHp() * hp) / 100.0);
    }
}
