package integra.pesintegra.Controllers;

import android.content.Context;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllUserPostActivity;
import integra.pesintegra.Services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorServeisAllPostUser extends ControladorServeis {
    private AllUserPostActivity activity;
    private Context context;

    public ControladorServeisAllPostUser(AllUserPostActivity allposts, Context cont) {
        this.context = cont;
        this.activity = allposts;
    }

    public void loadFeedPosts (String owner) {
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> createCall2 = service.getAllPostsFromUser(owner);
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
