package integra.pesintegra.Services;

import java.util.ArrayList;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @GET("/users/get")
    Call<User> getUser(@Query("usr") String usr, @Query("pas") String pas);

    @POST("users/new")
    Call<Void> createUser(@Body User user);
}
