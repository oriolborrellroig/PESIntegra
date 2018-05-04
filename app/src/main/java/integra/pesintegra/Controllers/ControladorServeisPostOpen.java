package integra.pesintegra.Controllers;

import android.content.Context;
import android.net.Uri;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.PostActivity;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorServeisPostOpen extends ControladorServeis {
    private PostActivity activity;
    private Context context;

    public ControladorServeisPostOpen (PostActivity callActivity, Context cont) {
        this.activity = callActivity;
        this.context = cont;
    }


    public void deletePost (String postId) {
        PostService service = ServiceManager.getPostService();
        Call<Void> createCall = service.deletePost(postId);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void updateAddToHide (String userId, String postId) {
        UserService service = ServiceManager.getUserService();
        Call<Void> createCall = service.updateAddToHide(userId, postId);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void updateRemoveToHide (String userId, String postId) {
        UserService service = ServiceManager.getUserService();
        Call<Void> createCall = service.updateAddToHide(userId, postId);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}
