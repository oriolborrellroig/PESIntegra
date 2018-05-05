package integra.pesintegra.Controllers;

import android.content.Context;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorPresentacioAllPostsActivity extends ControladorPresentacio {

    private AllPostsActivity activity;
    private ControladorDominiAllPostsActivity CDAllPosts;
    private Context context;

    public ControladorPresentacioAllPostsActivity(AllPostsActivity allposts, Context cont) {
        this.context = cont;
        this.activity = allposts;
        this.CDAllPosts = new ControladorDominiAllPostsActivity(this);
    }

    public void loadFeedAnyPosts () {
        CDAllPosts.loadFeedAnyPosts();
    }

    public void loadFeedWorkPosts () {
        CDAllPosts.loadFeedWorkPosts();
    }

    public void loadFeedActivityPosts () {
        CDAllPosts.loadFeedActivityPosts();
    }

    public void loadFeedHousePosts () {
        CDAllPosts.loadFeedHousePosts();
    }

    public void loadFeedHiddenPosts () { CDAllPosts.loadFeedHiddenPosts(); }

    public void updateFeed(ArrayList<Post> posts) {
        activity.updateFeed(posts,context);
    }

}
