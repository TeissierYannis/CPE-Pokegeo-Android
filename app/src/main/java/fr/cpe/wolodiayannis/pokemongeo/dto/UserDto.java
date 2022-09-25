package fr.cpe.wolodiayannis.pokemongeo.dto;

public class UserDto {
    final String pseudo;
    final String email;
    final String password;

    public UserDto(String pseudo, String email, String password) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }
}
