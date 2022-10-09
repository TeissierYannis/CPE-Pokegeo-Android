package fr.cpe.wolodiayannis.pokemongeo.entity.item;

public class ItemBall extends Item {

    /**
     * Pokeball accuracy.
     */
    private final int accuracy;

    /**
     * Item constructor.
     *
     * @param id    Item id.
     * @param name  Item name.
     * @param price Item price.
     * @param precision Pokeball precision.
     */
    public ItemBall(int id, String name, int price, int precision) {
        super(id, name, price);
        this.accuracy = precision;
    }

    /**
     * Get pokeball precision.
     * @return Pokeball precision.
     */
    public int getAccuracy() {
        return accuracy;
    }
}
