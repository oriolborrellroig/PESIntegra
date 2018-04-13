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

public class ControladorServeisCreateActivity extends ControladorServeis {
    private CreateActivityActivity activity = new CreateActivityActivity();
    private Context context;

    public ControladorServeisCreateActivity (CreateActivityActivity callActivity, Context cont) {
        this.activity = callActivity;
        this.context = cont;
    }


    public void createPost (Post post, Uri uri) {
        PostService service = ServiceManager.getPostService();
        Call<Void> createCall = service.createPost(post);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                activity.showNewPost(context);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });


    }

}
