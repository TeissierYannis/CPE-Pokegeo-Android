package fr.cpe.wolodiayannis.pokemongeo.entity;

import java.sql.Timestamp;

public class User {
    private final int id;
    private final String pseudo;
    private final String password;
    private final String email;
    private int experience;
    private boolean is_init;
    private final Timestamp created_at;

    public User(int id, String pseudo, String password, String email, int experience, boolean is_init, Timestamp created_at) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
        this.experience = experience;
        this.is_init = is_init;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getExperience() {
        return experience;
    }

    public void addExperience(int experience) {
        this.experience += experience;
    }

    public boolean isIs_init() {
        return is_init;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }
}
