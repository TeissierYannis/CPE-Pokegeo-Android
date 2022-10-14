package fr.cpe.wolodiayannis.pokemongeo.entity.user;

public class UserMoneyAndExp {

    /**
     * User id.
     */
    Integer userId;

    /**
     * User money.
     */
    Integer money;

    /**
     * User experience.
     */
    Integer experience;


    /**
     * us
     * Constructor.
     *
     * @param userId User id.
     * @param money  User money.
     */
    public UserMoneyAndExp(int userId, int money, int experience) {
        this.userId = userId;
        this.money = money;
        this.experience = experience;
    }

}
