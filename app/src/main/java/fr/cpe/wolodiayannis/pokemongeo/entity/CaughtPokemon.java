package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.CaughtPokemonAdapter;

@JsonAdapter(CaughtPokemonAdapter.class)
public class CaughtPokemon implements Serializable {

    /**
     * user id.
     */
    @SerializedName("user_id")
    private final int userId;

    /**
     * pokemon id.
     */
    @SerializedName("pokemon_id")
    private final int pokemonId;

    /**
     * caught time.
     */
    @SerializedName("caught_time")
    private final Timestamp caughtTime;

    /**
     * pokemon experience.
     */
    @SerializedName("pokemon_experience")
    private int pokemonExperience;

    /**
     * current life state.
     */
    @SerializedName("current_life_state")
    private int currentLifeState;

    /**
     * Constructor.
     * @param userId User ID
     * @param caughtTime Caught time
     * @param pokemonExperience Pokemon experience
     * @param currentLifeState Current life state
     */
    public CaughtPokemon(int userId, int pokemonId, int pokemonExperience, int currentLifeState, Timestamp caughtTime) {
        this.userId = userId;;
        this.pokemonId = pokemonId;
        this.caughtTime = caughtTime;
        this.pokemonExperience = pokemonExperience;
        this.currentLifeState = currentLifeState;
    }

    /**
     * Get the user id.
     * @return The user id.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Get the pokemon id.
     * @return The pokemon id.
     */
    public int getPokemonId() {
        return pokemonId;
    }

    /**
     * Get the caught time.
     * @return The caught time.
     */
    public Timestamp getCaughtTime() {
        return caughtTime;
    }

    /**
     * Get the pokemon experience.
     * @return The pokemon experience.
     */
    public int getPokemonExperience() {
        return pokemonExperience;
    }

    /**
     * Set the pokemon experience.
     * @param pokemon_experience The pokemon experience.
     */
    public void setPokemonExperience(int pokemon_experience) {
        this.pokemonExperience = pokemon_experience;
    }

    /**
     * Get the current life state.
     * @return The current life state.
     */
    public int getCurrentLifeState() {
        return currentLifeState;
    }

    /**
     * Set the current life state.
     * @param current_life_state The current life state.
     */
    public void setCurrentLifeState(int current_life_state) {
        this.currentLifeState = current_life_state;
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
        return getUserId() == that.getUserId() && getPokemonExperience() == that.getPokemonExperience() && getCurrentLifeState() == that.getCurrentLifeState() && Objects.equals(getCaughtTime(), that.getCaughtTime());
    }

    /**
     * Get the hash code of the caught pokemon.
     * @return The hash code of the caught pokemon.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getCaughtTime(), getPokemonExperience(), getCurrentLifeState());
    }
}
