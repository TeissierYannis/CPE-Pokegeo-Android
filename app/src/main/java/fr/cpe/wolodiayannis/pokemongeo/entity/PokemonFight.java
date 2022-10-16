package fr.cpe.wolodiayannis.pokemongeo.entity;

import fr.cpe.wolodiayannis.pokemongeo.R;

public class PokemonFight {

    /**
     * The pokemon that is fighting (player)
     */
    private Pokemon playerPokemon;
    /**
     * The pokemon that is fighting (opponent)
     */
    private final Pokemon opponentPokemon;

    /**
     * Attacker life points
     */
    private int playerLifePoints;

    /**
     * Defender life points
     */
    private int opponentLifePoints;

    /**
     * Constructor.
     *
     * @param playerPokemon   The pokemon that is fighting (player)
     * @param opponentPokemon The pokemon that is fighting (opponent)
     */
    public PokemonFight(Pokemon playerPokemon, Pokemon opponentPokemon) {
        this.playerPokemon = playerPokemon;
        this.opponentPokemon = opponentPokemon;
        this.playerLifePoints = playerPokemon.getHp();
        this.opponentLifePoints = opponentPokemon.getHp();
    }

    /**
     * Constructor.
     *
     * @param playerPokemon      The pokemon that is fighting (player)
     * @param opponentPokemon    The pokemon that is fighting (opponent)
     * @param playerLifePoints   Attacker life points
     * @param opponentLifePoints Defender life points
     */
    public PokemonFight(Pokemon playerPokemon, Pokemon opponentPokemon, int playerLifePoints, int opponentLifePoints) {
        this.playerPokemon = playerPokemon;
        this.opponentPokemon = opponentPokemon;
        this.playerLifePoints = playerLifePoints;
        this.opponentLifePoints = opponentLifePoints;
    }

    /**
     * Attack the opponent.
     *
     * @param attacker The attacker.
     * @param defender The defender.
     */
    public void attack(Pokemon attacker, Pokemon defender) {
        double damage = ((this.getDamageMultiplier(attacker, defender) * attacker.getAttack()) / defender.getDefense()) * 3;

        // 5% chance to critical hit
        if (Math.random() < 0.05) {
            damage *= 2;
        }

        if (attacker == playerPokemon) {
            System.out.println("Player attacks " + defender.getName() + " for " + damage + " damage");
            this.opponentLifePoints -= damage;
        } else {
            System.out.println("Opponent attacks " + defender.getName() + " for " + damage + " damage");
            this.playerLifePoints -= damage;
        }
    }


    /**
     * Get damage multiplier in fact of the type of the pokemon.
     *
     * @return The damage multiplier.
     */
    public double getDamageMultiplier(Pokemon attacker, Pokemon defender) {
        return 1;
    }

    /**
     * Is player pokemon dead ?
     *
     * @return True if dead, false otherwise.
     */
    public boolean isPlayerPokemonDead() {
        return this.playerLifePoints <= 0;
    }

    /**
     * Is opponent pokemon dead ?
     *
     * @return True if dead, false otherwise.
     */
    public boolean isOpponentPokemonDead() {
        return this.opponentLifePoints <= 0;
    }

    /**
     * Get life bar color.
     *
     * @return The color.
     */
    public int getEnemyProgressBarColor() {
        if (this.opponentLifePoints > this.opponentPokemon.getHp() / 2) {
            return R.color.green;
        } else if (this.opponentLifePoints > this.opponentPokemon.getHp() / 4) {
            return R.color.orange;
        } else {
            return R.color.red;
        }
    }

    /**
     * Get life bar color.
     *
     * @return The color.
     */
    public int getPlayerProgressBarColor() {
        if (this.playerLifePoints > this.playerPokemon.getHp() / 2) {
            return R.color.green;
        } else if (this.playerLifePoints > this.playerPokemon.getHp() / 4) {
            return R.color.orange;
        } else {
            return R.color.red;
        }
    }

    /**
     * Change pokemon.
     *
     * @param pokemon          The new pokemon.
     * @param currentLifeState The current life state of the pokemon.
     */
    public void switchPlayerPokemon(Pokemon pokemon, int currentLifeState) {
        this.playerPokemon = pokemon;
        this.playerLifePoints = currentLifeState;
    }

    /**
     * Player pokemon.
     *
     * @return the playerPokemon
     */
    public Pokemon getPlayerPokemon() {
        return this.playerPokemon;
    }

    /**
     * Get the opponent pokemon.
     *
     * @return the opponentPokemon
     */
    public Pokemon getOpponentPokemon() {
        return this.opponentPokemon;
    }

    /**
     * Set the player pokemon.
     *
     * @param playerPokemon the playerPokemon to set
     */
    public void setPlayerPokemon(Pokemon playerPokemon) {
        this.playerPokemon = playerPokemon;
    }

    /**
     * Get the player life points.
     *
     * @return the playerLifePoints
     */
    public int getPlayerLifePoints() {
        return this.playerLifePoints;
    }

    /**
     * Heal the player pokemon.
     *
     * @param newLifePoints The new life points.
     */
    public void healPlayerPokemon(int newLifePoints) {
        this.playerLifePoints = newLifePoints;
    }

    /**
     * Get the opponent life points.
     *
     * @return the opponentLifePoints
     */
    public int getOpponentLifePoints() {
        return this.opponentLifePoints;
    }
}
