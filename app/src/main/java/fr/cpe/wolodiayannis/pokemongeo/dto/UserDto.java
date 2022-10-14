package fr.cpe.wolodiayannis.pokemongeo.dto;

public class UserDto {
    /**
     * Pseudo.
     */
    final String pseudo;
    /**
     * Email.
     */
    final String email;
    /**
     * Password.
     */
    final String password;

    /**
     * Constructor.
     *
     * @param pseudo   pseudo
     * @param password password
     */
    public UserDto(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
        this.email = null;
    }

    /**
     * Constructor.
     *
     * @param pseudo   pseudo
     * @param email    email
     * @param password password
     */
    public UserDto(String pseudo, String email, String password) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }
}
