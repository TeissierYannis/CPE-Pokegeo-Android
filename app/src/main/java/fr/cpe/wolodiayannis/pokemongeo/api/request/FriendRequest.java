package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.FriendAPI;
import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.dto.UserFriendDto;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.FriendList;
import retrofit2.Call;

public class FriendRequest extends BaseRequest {

    /**
     * Get UserAPI.
     *
     * @return UserAPI.
     */
    protected static FriendAPI getAPI() {
        return getRetrofit().create(FriendAPI.class);
    }

    /**
     * Get the friend list for a user.
     * @return FriendList
     */
    public static FriendList getFriendList() {
        Call<FriendList> call = getAPI().getFriendList(Datastore.getInstance().getUser().getId());
        try {
            FriendList friendList = call.execute().body();
            if (friendList == null) {
                friendList = new FriendList();
            }
            return friendList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the pending friend list for a user.
     * @return FriendList
     */
    public static FriendList getPendingFriendList() {
        Call<FriendList> call = getAPI().getPendingFriendList(Datastore.getInstance().getUser().getId());
        try {
            FriendList friendList = call.execute().body();
            if (friendList == null) {
                friendList = new FriendList();
            }
            return friendList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Add a friend to the user.
     * @param pseudo Friend pseudo.
     * @return
     */
    public static BasicResponse addFriend(String pseudo) {
        System.out.println("Add friend " + pseudo);
        UserFriendDto userFriendDto = new UserFriendDto(Datastore.getInstance().getUser().getId(), pseudo);
        Call<BasicResponse> call = getAPI().addFriend(userFriendDto);
        try {
            BasicResponse response = call.execute().body();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * accept a friend from the user.
     * @param friendId Friend id.
     * @return
     */
    public static BasicResponse acceptFriend(int friendId) {
        UserFriendDto userFriendDto = new UserFriendDto(Datastore.getInstance().getUser().getId(), friendId);
        Call<BasicResponse> call = getAPI().acceptFriend(userFriendDto);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * refuse a friend from the user.
     * @param friendId Friend id.
     * @return
     */
    public static BasicResponse declineFriend(int friendId) {
        UserFriendDto userFriendDto = new UserFriendDto(Datastore.getInstance().getUser().getId(), friendId);
        Call<BasicResponse> call = getAPI().declineFriend(userFriendDto);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
