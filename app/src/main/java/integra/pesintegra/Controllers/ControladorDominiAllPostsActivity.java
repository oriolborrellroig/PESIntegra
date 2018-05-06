package integra.pesintegra.Controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Presentation.LoginActivity;
import integra.pesintegra.Services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiAllPostsActivity extends ControladorDomini {

    private ControladorPresentacioAllPostsActivity Cpresentacio;
    private Context context;
    private boolean h;

    public ControladorDominiAllPostsActivity(ControladorPresentacioAllPostsActivity allposts) {
        this.Cpresentacio = allposts;
        h = false;
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

    public void loadFeedUserPosts () {
        //TODO: agafar el current user enlloc de "1"
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> call = service.getAllPostsFromUser("1");
        enqueueCall(call);
    }
    public void loadFeedHousePosts () {
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("home");
        enqueueCall(call);
    }

    public void loadFeedHiddenPosts() {
        PostService service = this.getServiceManager().getPostService();

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Cal descomentar quan estigui el id a sessio i la crida a bd:
        //lo de sota
        //getSessioID de ControladorDomini
        //getID a Sessio
        //TODO: agafar el current user enlloc de "1"
        Call<ArrayList<Post>> call = service.getHiddenListFromUser("1");
        h = true;
        enqueueCall(call);
    }

    private void enqueueCall (Call<ArrayList<Post>> call) {
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                //if(!h)
                    Cpresentacio.updateFeed(response.body());
                //else Cpresentacio.sendHiddenList(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
            }
        });
    }


}
