package fr.cpe.wolodiayannis.pokemongeo.entity;

public class UserPokedex {
    private final int user_id;
    private final int pokemon_id;
    private int quantity_caught;
    private int quantity_seen;

    public UserPokedex(int user_id, int pokemon_id, int quantity_caught, int quantity_seen) {
        this.user_id = user_id;
        this.pokemon_id = pokemon_id;
        this.quantity_caught = quantity_caught;
        this.quantity_seen = quantity_seen;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public int getQuantity_caught() {
        return quantity_caught;
    }

    public int getQuantity_seen() {
        return quantity_seen;
    }

    public void addQuantity_caught() {
        this.quantity_caught++;
    }

    public void addQuantity_seen() {
        this.quantity_seen++;
    }
}
