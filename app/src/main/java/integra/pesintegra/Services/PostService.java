package integra.pesintegra.Services;
import java.util.List;

import integra.pesintegra.Logic.Clases.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostService {

    @GET("testing")
    Call<Void> getPost();

    /*@GET("books/{isbn}")
    Call<Book> get(@Path("isbn") String isbn);

    @POST("books/new")
    Call<Book> create(@Body Book book);*/
}
