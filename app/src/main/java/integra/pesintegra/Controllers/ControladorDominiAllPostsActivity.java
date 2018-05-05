package integra.pesintegra.Controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiAllPostsActivity extends ControladorDomini {

    private ControladorPresentacioAllPostsActivity Cpresentacio;
    private Context context;

    public ControladorDominiAllPostsActivity(ControladorPresentacioAllPostsActivity allposts) {
        this.Cpresentacio = allposts;
    }

    public void loadFeedAnyPosts () {
        PostService service = this.getServiceManager().getPostService();
        Log.d("TOOKKKK",this.getSessioToken());
        Log.d("TOOsadasdsadKKKK",this.getSessioUser());
        Call<ArrayList<Post>> call = service.getAllPosts("any");
        enqueueCall(call);
    }

    public void loadFeedWorkPosts () {
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("work");
        enqueueCall(call);
    }

    public void loadFeedActivityPosts () {
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("activity");
        enqueueCall(call);
    }

    public void loadFeedHousePosts () {
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("home");
        enqueueCall(call);
    }

    private void enqueueCall (Call<ArrayList<Post>> call) {
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                Cpresentacio.updateFeed(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
            }
        });
    }
}
