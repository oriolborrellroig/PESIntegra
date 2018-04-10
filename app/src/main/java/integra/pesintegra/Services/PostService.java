package integra.pesintegra.Services;
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

    @GET("post/get")
    Call<Post> getPost(@Query("id") String id);

    /*@GET("books/{isbn}")
    Call<Book> get(@Path("isbn") String isbn);

    @POST("books/new")
    Call<Book> create(@Body Book book);*/

    @POST("post/new")
    Call<Post> createActivity(@Body Post post);
}
