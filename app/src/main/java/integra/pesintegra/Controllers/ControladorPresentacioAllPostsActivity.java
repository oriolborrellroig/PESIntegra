package integra.pesintegra.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Logic.Adapter.ListAdapter;
import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllPostsActivity;


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

    public void loadFeedAnyPosts (Integer order) {
        CDAllPosts.loadFeedAnyPosts(order);
    }

    public void loadFeedWorkPosts (Integer order) {
        CDAllPosts.loadFeedWorkPosts(order);
    }

    public void loadFeedActivityPosts (Integer order) {
        CDAllPosts.loadFeedActivityPosts(order);
    }

    public void loadFeedHousePosts (Integer order) {
        CDAllPosts.loadFeedHousePosts(order);
    }

    public static void loadFeedHiddenPosts () { CDAllPosts.loadFeedHiddenPosts(); }

    public static void loadFeedUserPropiPosts (String user) { CDAllPosts.loadFeedUserPosts(user); }

    public static void updateFeed(ArrayList<Post> posts) {
        AllPostsActivity.updateFeed(posts,context);
    }

    public static void loadFeedTagsPosts(ArrayList<String> listtags) {
        CDAllPosts.loadFeedTagsPosts(listtags);
    }

    public static void loadFeedTaCalendarPosts(String id) {
        CDAllPosts.loadFeedCalendarPosts(id);
    }

    public void loadFeedAdvSearch(Bundle extras) { CDAllPosts.loadFeedAdvSearch(extras); }


    public static void loadReportedPosts() { CDAllPosts.loadReportedPosts();}

    public List<String> getTagsSessio(){
        return CDAllPosts.getTagsSessio();
    }

    public void loadTagsSessio() {
        CDAllPosts.loadTagsSessio();
    }

    public void setImage(AllPostsActivity act) {
        activity = act;
        CDAllPosts.setImageDrawer(this);
    }

    public void getImageResponse(ImageBM body) {
        activity.loadImage(body.getBitmapImage());
    }

    public void getReportedCommentedPosts(){ CDAllPosts.loadReportedCommentedPosts(); }

    public void afegir_imatge(String id, ImageView icon_post, ListAdapter listAdapter) {
        CDAllPosts.posa_imatge(id, icon_post, listAdapter);
    }
}
