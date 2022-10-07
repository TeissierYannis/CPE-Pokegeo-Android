package fr.cpe.wolodiayannis.pokemongeo.entity;

public class ItemPokeball extends Item {

    /**
     * Pokeball precision.
     */
    private final int precision;

    /**
     * Item constructor.
     *
     * @param id    Item id.
     * @param name  Item name.
     * @param price Item price.
     * @param precision Pokeball precision.
     */
    public ItemPokeball(int id, String name, int price, int precision) {
        super(id, name, price);
        this.precision = precision;
    }

    /**
     * Get pokeball precision.
     * @return Pokeball precision.
     */
    public int getPrecision() {
        return precision;
    }
}
