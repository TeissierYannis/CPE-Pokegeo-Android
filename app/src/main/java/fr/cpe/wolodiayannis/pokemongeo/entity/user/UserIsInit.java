package fr.cpe.wolodiayannis.pokemongeo.entity.user;

public class UserIsInit {

    /**
     * User id.
     */
    Integer id;
    /**
     * User is init ?.
     */
    Boolean isInit;

    /**
     * Constructor.
     * @param userId User id.
     * @param isInit User is init ?
     */
    public UserIsInit(int userId, boolean isInit) {
        this.id = userId;
        this.isInit = isInit;
    }
}
