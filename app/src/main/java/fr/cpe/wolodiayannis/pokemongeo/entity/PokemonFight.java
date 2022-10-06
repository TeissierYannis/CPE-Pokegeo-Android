package fr.cpe.wolodiayannis.pokemongeo.entity;

public class PokemonFight {

    /**
     * The pokemon that is fighting (player)
     */
    private Pokemon playerPokemon;
    /**
     * The pokemon that is fighting (opponent)
     */
    private Pokemon opponentPokemon;

    /**
     * Attacker life points
     */
    private int playerLifePoints;

    /**
     * Defender life points
     */
    private int opponentLifePoints;

    public PokemonFight(Pokemon playerPokemon, Pokemon opponentPokemon) {
        this.playerPokemon = playerPokemon;
        this.opponentPokemon = opponentPokemon;
    }

    public void attack(Pokemon attacker, Pokemon defender) {
        int damage = attacker.getAttack() - defender.getDefense();
        if (damage < 0) {
            damage = 0;
        }
        // Check if attack is opponent or player
        if (attacker.equals(playerPokemon)) {
            opponentLifePoints -= damage;
            if (isOpponentPokemonDead()) {
                System.out.println("You win !");
            }
        } else {
            playerLifePoints -= damage;
            if (isPlayerPokemonDead()) {
                System.out.println("You lose !");
            }
        }
    }

    public boolean isPlayerPokemonDead() {
        return playerLifePoints <= 0;
    }

    public boolean isOpponentPokemonDead() {
        return opponentLifePoints <= 0;
    }

    /**
     * Player pokemon.
     *
     * @return the playerPokemon
     */
    public Pokemon getPlayerPokemon() {
        return playerPokemon;
    }

    /**
     * Get the opponent pokemon.
     *
     * @return the opponentPokemon
     */
    public Pokemon getOpponentPokemon() {
        return opponentPokemon;
    }

    /**
     * Set the player pokemon.
     * @param playerPokemon the playerPokemon to set
     */
    public void setPlayerPokemon(Pokemon playerPokemon) {
        this.playerPokemon = playerPokemon;
    }

    /**
     * Get the player life points.
     * @return the playerLifePoints
     */
    public int getPlayerLifePoints() {
        return playerLifePoints;
    }

    /**
     * Get the opponent life points.
     * @return the opponentLifePoints
     */
    public int getOpponentLifePoints() {
        return opponentLifePoints;
    }
}
