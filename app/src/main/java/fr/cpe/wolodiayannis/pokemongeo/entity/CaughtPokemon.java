package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.security.Timestamp;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.CaughtPokemonListAdapter;

@JsonAdapter(CaughtPokemonListAdapter.class)
public class CaughtPokemon {

    @SerializedName("user_id")
    private final int user_id;

    private final Pokemon pokemon;

    @SerializedName("caught_time")
    private final Timestamp caught_time;

    @SerializedName("pokemon_experience")
    private int pokemon_experience;

    @SerializedName("current_life_state")
    private int current_life_state;

    public CaughtPokemon(int user_id, Pokemon pokemon, Timestamp caught_time, int pokemon_experience, int current_life_state) {
        this.user_id = user_id;
        this.pokemon = pokemon;
        this.caught_time = caught_time;
        this.pokemon_experience = pokemon_experience;
        this.current_life_state = current_life_state;
    }

    /**
     * Get the user id.
     * @return The user id.
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Get the pokemon.
     * @return The pokemon.
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * Get the caught time.
     * @return The caught time.
     */
    public Timestamp getCaught_time() {
        return caught_time;
    }

    /**
     * Get the pokemon experience.
     * @return The pokemon experience.
     */
    public int getPokemon_experience() {
        return pokemon_experience;
    }

    /**
     * Set the pokemon experience.
     * @param pokemon_experience The pokemon experience.
     */
    public void setPokemon_experience(int pokemon_experience) {
        this.pokemon_experience = pokemon_experience;
    }

    /**
     * Get the current life state.
     * @return The current life state.
     */
    public int getCurrent_life_state() {
        return current_life_state;
    }

    /**
     * Set the current life state.
     * @param current_life_state The current life state.
     */
    public void setCurrent_life_state(int current_life_state) {
        this.current_life_state = current_life_state;
    }

    /**
     * Check if two caught pokemons are equals.
     * @param o Caught pokemon to compare.
     * @return True if equals, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaughtPokemon)) return false;
        CaughtPokemon that = (CaughtPokemon) o;
        return getUser_id() == that.getUser_id() && getPokemon_experience() == that.getPokemon_experience() && getCurrent_life_state() == that.getCurrent_life_state() && Objects.equals(getPokemon(), that.getPokemon()) && Objects.equals(getCaught_time(), that.getCaught_time());
    }

    /**
     * Get the hash code of the caught pokemon.
     * @return The hash code of the caught pokemon.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getPokemon(), getCaught_time(), getPokemon_experience(), getCurrent_life_state());
    }
}
