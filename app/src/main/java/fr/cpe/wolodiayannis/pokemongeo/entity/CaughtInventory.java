package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.sql.Timestamp;

/**
 * CaughtInventory class.
 */
public class CaughtInventory {

    private final int user_id;
    private final int pokemon_id;
    private int pokemon_experience;
    private int current_life_state;
    private final Timestamp caught_time;

    /**
     * Add a pokemon to the list.
     * @param pokemon Pokemon to add.
     */
    public CaughtInventory(int user_id, int pokemon_id, int pokemon_experience, int current_life_state, Timestamp caught_time) {
        this.user_id = user_id;
        this.pokemon_id = pokemon_id;
        this.pokemon_experience = pokemon_experience;
        this.current_life_state = current_life_state;
        this.caught_time = caught_time;

        // TODO maj sur la bdd
    }

    public int getUser_id() {
        return user_id;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public int getPokemon_experience() {
        return pokemon_experience;
    }

    public void setPokemon_experience(int pokemon_experience) {
        this.pokemon_experience = pokemon_experience;
        // TODO maj sur la bdd
    }

    public int getCurrent_life_state() {
        return current_life_state;
    }

    public void setCurrent_life_state(int current_life_state) {
        this.current_life_state = current_life_state;
        // TODO maj sur la bdd
    }

    public Timestamp getCaught_time() {
        return caught_time;
    }
}
