package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;
import fr.cpe.wolodiayannis.pokemongeo.dto.UserDto;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.UserIsInit;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.UserMoneyAndExp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserAPI extends BaseAPI {

    /**
     * POST the new user
     *
     * @param userLoginDto user info
     * @return user
     */
    @POST("login")
    Call<User> getUser(@Body UserDto userLoginDto);

    /**
     * POST the new user
     *
     * @param userDto user info
     * @return user
     */
    @POST("register")
    Call<User> createUser(@Body UserDto userDto);

    /**
     * Update the user init
     * @param userIsInit user init
     * @return UserIsInit
     */
    @PUT("user/update/is_init")
    Call<UserIsInit> updateUserIsInit(@Body UserIsInit userIsInit);

    /**
     * Update the user money and exp
     * @param userMoneyAndExp user money and exp
     * @return UserMoneyAndExp
     */
    @PUT("user/update/moneyExp")
    Call<BasicResponse> updateUserMoneyAndExp(@Body UserMoneyAndExp userMoneyAndExp);

    /**
     * logout the user
     * @param userDto user info
     * @return BasicResponse
     */
    @POST("/logout")
    Call<BasicResponse> logoutUser(@Body UserDto userDto);
}

