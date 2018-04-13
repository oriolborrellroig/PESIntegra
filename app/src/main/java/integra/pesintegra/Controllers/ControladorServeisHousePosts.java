package integra.pesintegra.Controllers;

import android.content.Context;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Presentation.HousePostActivity;
import integra.pesintegra.Services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorServeisHousePosts extends ControladorServeis {

    private HousePostActivity activity;
    private Context context;

    public ControladorServeisHousePosts(HousePostActivity allposts, Context cont) {
        this.context = cont;
        this.activity = allposts;
    }
    public void loadFeedPosts () {
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> createCall2 = service.getAllPosts("home");
        createCall2.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                activity.updateFeed(response.body(), context);
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
            }
        });
    }
}
