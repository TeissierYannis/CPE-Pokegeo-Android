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
     * User name.
     */
    @SerializedName("password")
    private final String password;

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
    private final int experience;

    /**
     * User is init
     * If the user as already played
     */
    @SerializedName("is_init")
    private final boolean isInit;

    /**
     * Timestamp when the user was created.
     */
    @SerializedName("created_at")
    private final Timestamp createdAt;

    /**
     * User constructor.
     * @param id User id.
     * @param password User name.
     * @param pseudo User pseudo.
     * @param email User email.
     * @param experience User experience.
     * @param isInit User is init.
     * @param createdAt Timestamp when the user was created.
     */
    public User(int id, String pseudo, String email, String password, int experience, boolean isInit, Timestamp createdAt) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.experience = experience;
        this.isInit = isInit;
        this.createdAt = createdAt;
    }

    /**
     * Get user id.
     * @return User id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get user name.
     * @return User name.
     */
    public String getPassword() {
        return password;
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
    public boolean getIsInit() {
        return isInit;
    }

    /**
     * Get timestamp when the user was created.
     * @return Timestamp when the user was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }
}