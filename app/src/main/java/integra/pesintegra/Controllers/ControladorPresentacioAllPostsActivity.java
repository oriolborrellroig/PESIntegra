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

    private static AllPostsActivity activity;
    private static ControladorDominiAllPostsActivity CDAllPosts;
    private static Context context;

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

    public static void loadFeedHiddenPosts () { CDAllPosts.loadFeedHiddenPosts(); }

    public static void loadFeedUserPropiPosts () { CDAllPosts.loadFeedUserPosts(); }

    public static void updateFeed(ArrayList<Post> posts) {
        activity.updateFeed(posts,context);
    }

    public static void loadFeedTagsPosts() {
        CDAllPosts.loadFeedTagsPosts();
    }

    public static void loadFeedTaCalendarPosts() {
        CDAllPosts.loadFeedCalendarPosts();
    }
/*
    public void sendHiddenList(ArrayList<Post> postsH){
        ArrayList<String> r = new ArrayList<>();
        for(int i = 0; i<postsH.size();++i){
            r.add(postsH.get(i).getId());
        }
        activity.setHiddenList(r);
    }*/

}
