package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;
import fr.cpe.wolodiayannis.pokemongeo.dto.UserFriendDto;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.FriendList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FriendAPI extends BaseAPI {

    /**
     * Get friends of the user
     *
     * @param userId user id
     * @return friend list
     */
    @GET("friends/list/accepted/{userId}")
    Call<FriendList> getFriendList(@Path("userId") int userId);

    /**
     * Get pending friends of the user
     *
     * @param userId user id
     * @return friend list
     */
    @GET("friends/list/pending/{userId}")
    Call<FriendList> getPendingFriendList(@Path("userId") int userId);

    /**
     * Add a friend
     *
     * @param userFriendDto user friend info
     * @return user
     */
    @POST("friends/request")
    Call<BasicResponse> addFriend(@Body UserFriendDto userFriendDto);

    /**
     * Accept a friend
     *
     * @param userFriendDto user friend info
     * @return user
     */
    @POST("friends/accept")
    Call<BasicResponse> acceptFriend(@Body UserFriendDto userFriendDto);

    /**
     * Delete a friend
     *
     * @param userFriendDto user friend info
     * @return user
     */
    @POST("friends/decline")
    Call<BasicResponse> declineFriend(@Body UserFriendDto userFriendDto);
}

