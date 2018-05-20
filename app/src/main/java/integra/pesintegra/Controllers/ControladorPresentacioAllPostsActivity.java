package integra.pesintegra.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorPresentacioAllPostsActivity extends ControladorPresentacio {

    @SuppressLint("StaticFieldLeak")
    private static AllPostsActivity activity;
    private static ControladorDominiAllPostsActivity CDAllPosts;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public ControladorPresentacioAllPostsActivity(AllPostsActivity allposts, Context cont) {
        context = cont;
        activity = allposts;
        CDAllPosts = new ControladorDominiAllPostsActivity();
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
        AllPostsActivity.updateFeed(posts,context);
    }

    public static void loadFeedTagsPosts() {
        CDAllPosts.loadFeedTagsPosts();
    }

    public static void loadFeedTaCalendarPosts() {
        CDAllPosts.loadFeedCalendarPosts();
    }

    public void loadFeedAdvSearch(Bundle extras) { CDAllPosts.loadFeedAdvSearch(extras); }



}
