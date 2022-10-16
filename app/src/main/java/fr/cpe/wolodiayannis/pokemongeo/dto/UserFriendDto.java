package fr.cpe.wolodiayannis.pokemongeo.dto;

public class UserFriendDto {

    int userId;
    String friendPseudo;
    int friendId;

    public UserFriendDto() {
    }

    public UserFriendDto(int userId, String friendPseudo) {
        this.userId = userId;
        this.friendPseudo = friendPseudo;
        this.friendId = 0;
    }

    public UserFriendDto(int userId, int friendId) {
        this.userId = userId;
        this.friendPseudo = "";
        this.friendId = friendId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFriendPseudo() {
        return friendPseudo;
    }

    public void setFriendPseudo(String friendPseudo) {
        this.friendPseudo = friendPseudo;
    }
}
