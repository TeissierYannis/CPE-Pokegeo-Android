package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.util.LinkedList;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.FriendListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.FriendRequest;

@JsonAdapter(FriendListAdapter.class)
public class FriendList {

    LinkedList<FriendRequest> friendList;

    public FriendList() {
        friendList = new LinkedList<>();
    }

    public FriendList(LinkedList<FriendRequest> friendList) {
        this.friendList = friendList;
    }

    public LinkedList<FriendRequest> getFriendList() {
        return friendList;
    }

    public void setFriendList(LinkedList<FriendRequest> friendList) {
        this.friendList = friendList;
    }

    public void addRequest(FriendRequest request) {
        friendList.add(request);
    }
}
