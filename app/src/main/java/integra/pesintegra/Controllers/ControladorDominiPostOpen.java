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

public class ControladorDominiPostOpen extends ControladorDomini {
    private PostActivity activity;
    private Context context;
    private static ControladorPresentacioPostOpen Cpresentacio;

    public ControladorDominiPostOpen (ControladorPresentacioPostOpen cPresentacio) {
        Cpresentacio = cPresentacio;
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
        Call<Void> createCall = service.updateRemoveToHide(userId, postId);
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
