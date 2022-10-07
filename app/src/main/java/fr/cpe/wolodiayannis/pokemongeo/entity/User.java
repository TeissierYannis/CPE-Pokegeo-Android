package fr.cpe.wolodiayannis.pokemongeo.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.UserAdapter;

/**
 * User entity class.
 */
@JsonAdapter(UserAdapter.class)
public class User implements Serializable  {
    /**
     * User id.
     */
    @SerializedName("id")
    private final int id;
    /**
     * User pseudo.
     */
    @SerializedName("pseudo")
    private final String pseudo;

    /**
     * User email.
     */
    @SerializedName("email")
    private final String email;

    /**
     * User experience.
     */
    @SerializedName("experience")
    private int experience;

    /**
     * User is init
     * If the user as already played
     */
    @SerializedName("is_init")
    private boolean isInit;

    /**
     * Timestamp when the user was created.
     */
    @SerializedName("created_at")
    private final Timestamp createdAt;

    /**
     * User money.
     */
    @SerializedName("money")
    private int money;

    /**
     * User JWT.
     */
    @SerializedName("token")
    private String jwt;

    /**
     * User constructor.
     * @param id User id.
     * @param pseudo User pseudo.
     * @param email User email.
     * @param experience User experience.
     * @param isInit User is init.
     * @param createdAt Timestamp when the user was created.
     * @param money User money.
     */
    public User(int id, String pseudo, String email, int experience,  int money, boolean isInit, Timestamp createdAt, String jwt) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.experience = experience;
        this.isInit = isInit;
        this.createdAt = createdAt;
        this.jwt = jwt;
        this.money = money;
    }

    /**
     * Get user id.
     * @return User id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get user pseudo.
     * @return User pseudo.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Get user email.
     * @return User email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get user experience.
     * @return User experience.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Get user is init.
     * @return User is init.
     */
    public boolean isInit() {
        return isInit;
    }

    /**
     * Set the user init.
     * @param init true if init.
     */
    public void setInit(boolean init) {
        isInit = init;
    }

    /**
     * Get timestamp when the user was created.
     * @return Timestamp when the user was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Get user JWT.
     * @return User JWT.
     */
    public String getJwt() {
        return jwt;
    }

    /**
     * Get user money.
     * @return User money.
     */
    public int getMoney() {
        return money;
    }
}