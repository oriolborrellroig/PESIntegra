package integra.pesintegra.Controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Presentation.CreateActivityActivity;
import integra.pesintegra.Presentation.PostActivity;
import integra.pesintegra.Services.ImageService;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiCreateActivity extends ControladorDomini {

    private ControladorPresentacioCreateActivity Cpresentacio;


    public ControladorDominiCreateActivity(ControladorPresentacioCreateActivity allposts) {
        this.Cpresentacio = allposts;
    }


    public void createPost (Post post, Uri uri) {
        post.setOwner(this.getSessioUser());
        PostService service = ServiceManager.getPostService();
        Call<Void> createCall = service.createPost(post);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Cpresentacio.showNewPost();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });


    }

}
