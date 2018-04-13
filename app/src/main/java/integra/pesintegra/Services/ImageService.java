package integra.pesintegra.Services;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImageService {


    @GET("/image/{image_id}")
    Call<ImageBM> getImage(@Path(value = "image_id", encoded = true) String imageId);

    @Multipart
    @POST("/image/{image_id}")
    Call<Void> createImage(@Path(value = "image_id", encoded = true) String imageId,  @Part MultipartBody.Part file);


}
