package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import fr.cpe.wolodiayannis.pokemongeo.entity.lists.FriendList;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.FriendRequest;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;

/**
 * Item View Model.
 */
public class FriendViewModel extends BaseObservable {

    /**
     * Friend list
     */
    private FriendRequest friendRequest;

    /**
     * Set friend request.
     * @param friendRequest friend request
     */
    public void setFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    /**
     * Get friend pseudo.
     * @return friend pseudo
     */
    @Bindable
    public String getPseudo() {
        return friendRequest.getFriendPseudo();
    }

    /**
     * Get friend id.
     * @return friend id
     */
    @Bindable
    public int getId() {
        return friendRequest.getFriendId();
    }
}
