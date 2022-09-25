package fr.cpe.wolodiayannis.pokemongeo.api;

import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPI extends BaseAPI {
    /**
     * Get one user.
     * @param id User id.
     */
    @GET("user/{id}")
    Call<User> getUser(@Path("id") int id);

    @POST("user")
    Call<User> createUser(@Path("user") User user);
}

