package fr.cpe.wolodiayannis.pokemongeo.entity.user;

public class UserMoney {

    /**
     * User id.
     */
    Integer id;

    /**
     * User money.
     */
    Integer money;

    /**
     * User experience.
     */
    Integer experience;


    /**
     * Constructor.
     * @param userId User id.
     * @param money User money.
     */
    public UserMoney(int userId, int money, int experience) {
        this.id = userId;
        this.money = money;
        this.experience = experience;
    }

}
