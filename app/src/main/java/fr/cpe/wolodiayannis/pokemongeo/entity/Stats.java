package fr.cpe.wolodiayannis.pokemongeo.entity;

/**
 * Stats class.
 */
public class Stats {
    /**
     * Pokemon's HP.
     */
    private final int hp;
    /**
     * Pokemon's attack.
     */
    private final int attack;
    /**
     * Pokemon's defense.
     */
    private final int defense;
    /**
     * Pokemon's special attack.
     */
    private final int specialAttack;
    /**
     * Pokemon's special defense.
     */
    private final int specialDefense;
    /**
     * Pokemon's speed.
     */
    private final int speed;

    /**
     * Constructor.
     * @param hp Pokemon's HP.
     * @param attack Pokemon's attack.
     * @param defense Pokemon's defense.
     * @param specialAttack Pokemon's special attack.
     * @param specialDefense Pokemon's special defense.
     * @param speed Pokemon's speed.
     */
    public Stats(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    /**
     * Factory method.
     * @param hp Pokemon's HP.
     * @param attack Pokemon's attack.
     * @param defense Pokemon's defense.
     * @param specialAttack Pokemon's special attack.
     * @param specialDefense Pokemon's special defense.
     * @param speed Pokemon's speed.
     * @return Stats.
     */
    public static Stats CREATE(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        return new Stats(hp, attack, defense, specialAttack, specialDefense, speed);
    }

    /**
     * Getter.
     * @return Pokemon's HP.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Getter.
     * @return Pokemon's attack.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Getter.
     * @return Pokemon's defense.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Getter.
     * @return Pokemon's special attack.
     */
    public int getSpecialAttack() {
        return specialAttack;
    }

    /**
     * Getter.
     * @return Pokemon's special defense.
     */
    public int getSpecialDefense() {
        return specialDefense;
    }

    /**
     * Getter.
     * @return Pokemon's speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Getter.
     * @return Pokemon's total stats.
     */
    public int getTotal() {
        return hp + attack + defense + specialAttack + specialDefense + speed;
    }
}
