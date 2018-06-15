package integra.pesintegra.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

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
    } //TODO: esta hardcoded

    public void loadFeedWorkPosts (Integer order) {
        CDAllPosts.loadFeedWorkPosts(order);
    } //TODO: esta hardcoded

    public void loadFeedActivityPosts (Integer order) {
        CDAllPosts.loadFeedActivityPosts(order);
    } //TODO: esta hardcoded

    public void loadFeedHousePosts (Integer order) {
        CDAllPosts.loadFeedHousePosts(order);
    }//TODO: esta hardcoded

    public static void loadFeedHiddenPosts () { CDAllPosts.loadFeedHiddenPosts(); }

    public static void loadFeedUserPropiPosts (String user) { CDAllPosts.loadFeedUserPosts(user); }

    public static void updateFeed(ArrayList<Post> posts) {
        AllPostsActivity.updateFeed(posts,context);
    }

    public static void loadFeedTagsPosts(ArrayList<String> listtags) {
        //ArrayList<String> listtags = new ArrayList<String>() ;
        CDAllPosts.loadFeedTagsPosts(listtags);
    }//TODO: array buit, cal passar per parametre els tags en format d'arraylist

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
        //Log.d("hey", "22222222222222222");
        activity = act;
        CDAllPosts.setImageDrawer(this);
    }

    public void getImageResponse(ImageBM body) {
        //Log.d("hey", "55555555555555555555");
        activity.loadImage(body.getBitmapImage());
    }
}
