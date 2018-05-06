package integra.pesintegra.Services;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @GET("/users/login")
    Call<JsonObject> loginUser(@Query("usr") String usr, @Query("pas") String pas);

    @GET("/users/get")
    Call<User> getUser(@Query("usr") String usr);

    @POST("users/new")
    Call<Void> createUser(@Body User user);

    @PATCH("users/updateMail")
    Call<Void> updateMailUser(@Query("usrid") String userid, @Query("mail") String mail, @Query("pass") String pass);

    @PATCH("users/updateAddToHide")
    Call<Void> updateAddToHide(@Query("usrid") String userid, @Query("postid") String postid);

    @PATCH("users/updateRemoveToHide")
    Call<Void> updateRemoveToHide(@Query("usrid") String userid, @Query("postid") String postid);
}
