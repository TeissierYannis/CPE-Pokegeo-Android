package fr.cpe.wolodiayannis.pokemongeo.listeners;

import fr.cpe.wolodiayannis.pokemongeo.entity.user.FriendRequest;

public interface OnFriend {
    void onAccept(FriendRequest request);
    void onDecline(FriendRequest request);
}
