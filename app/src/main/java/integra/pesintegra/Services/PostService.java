package integra.pesintegra.Services;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostService {

    @GET("/post/get")
    Call<Post> getPost(@Query("id") String value);

    /*@GET("books/{isbn}")
    Call<Book> get(@Path("isbn") String isbn);

    @POST("books/new")
    Call<Book> create(@Body Book book);*/

   /* @GET("/post/all")
    Call<ArrayList<Post>> getAllPosts();*/

    @GET("/post/all")
    Call<ArrayList<Post>> getAllPosts(@Query("type") String type);

    @POST("post/new")
    Call<Post> createPost(@Body Post post);
}
