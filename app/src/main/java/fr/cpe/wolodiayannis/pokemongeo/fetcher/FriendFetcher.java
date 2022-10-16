package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;
import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.FriendList;

public class FriendFetcher {


    /**
     * Context.
     */
    private final Context ctx;

    /**
     * Constructor.
     *
     * @param ctx the context
     */
    public FriendFetcher(Context ctx) {
        this.ctx = ctx;
    }


    /**
     * Fetches the friend list.
     *
     * @return the friend list
     */
    public FriendList fetchFriendList() {

        FriendList friendList = new FriendList();
        try {

            friendList = DataFetcher.fetchFriendList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return friendList;
    }

    /**
     * Fetches the friend list.
     *
     * @return the friend list
     */
    public FriendList fetchPendingFriendList() {

        FriendList friendList = new FriendList();
        try {

            friendList = DataFetcher.fetchPendingFriendList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return friendList;
    }

    /**
     * Add a friend.
     * @return
     */
    public BasicResponse addFriend(String pseudo) {
        try {
            return DataFetcher.addFriend(pseudo);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Accept a friend.
     * @return
     */
    public BasicResponse acceptFriend(int friendId) {
        try {
            return DataFetcher.acceptFriend(friendId);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Decline a friend.
     * @return
     */
    public BasicResponse declineFriend(int friendId) {
        try {
            return DataFetcher.declineFriend(friendId);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }


}
