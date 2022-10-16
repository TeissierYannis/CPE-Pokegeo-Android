package fr.cpe.wolodiayannis.pokemongeo.entity.user;

public class FriendRequest {

    private final int friendId;
    private final String friendPseudo;

    public FriendRequest(int friendId, String friendPseudo) {
        this.friendId = friendId;
        this.friendPseudo = friendPseudo;
    }

    public int getFriendId() {
        return friendId;
    }

    public String getFriendPseudo() {
        return friendPseudo;
    }
}
