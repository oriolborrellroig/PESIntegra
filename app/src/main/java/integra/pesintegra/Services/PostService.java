package integra.pesintegra.Services;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostService {

    @GET("/post/get")
    Call<Post> getPost(@Query("id") String value);

    @GET("/post/all")
    Call<ArrayList<Post>> getAllPosts(@Query("type") String type);

    @GET("/post/allUser")
    Call<ArrayList<Post>> getAllPostsFromUser(@Query("owner") String owner);

    @GET("/post/allHiddenUser")
    Call<ArrayList<Post>> getHiddenListFromUser(@Query("owner") String owner);

    @POST("post/new")
    Call<Void> createPost(@Body Post post);

    @DELETE("/post/delete")
    Call<Void> deletePost(@Query("id") String value);
}
