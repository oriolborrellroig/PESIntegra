package integra.pesintegra.Services;


import integra.pesintegra.Logic.Clases.ImageBM;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ImageService {


    @GET("/image/getPost")
    Call<ImageBM> getImagePost(@Query ("idImage") String imageId);

    @POST("/image/newPost")
    Call<Void> createImagePost(@Body ImageBM pic);

    @GET("/image/getProfile")
    Call<ImageBM> getImageProfile(@Query ("idImage") String imageId);

    @POST("/image/newProfile")
    Call<Void> createImageProfile(@Body ImageBM pic);



}
