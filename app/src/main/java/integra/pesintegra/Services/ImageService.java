package integra.pesintegra.Services;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImageService {


    @GET("/image/getPost")
    Call<ImageBM> getImagePost(@Query ("idImage") String imageId);

    @POST("/image/newPost")
    Call<Void> createImage(@Body ImageBM pic);

    @PATCH("/image/update")
    Call<Void> updateImage(@Body ImageBM pic);



}
