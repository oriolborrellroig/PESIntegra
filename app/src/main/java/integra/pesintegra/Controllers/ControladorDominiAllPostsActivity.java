package integra.pesintegra.Controllers;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Presentation.LoginActivity;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiAllPostsActivity extends ControladorDomini {

    public void loadFeedAnyPosts () {
        PostService service = ServiceManager.getPostService();
        Log.d("TOOKKKK",this.getSessioToken());
        Log.d("TOOsadasdsadKKKK",this.getSessioUser());
        Call<ArrayList<Post>> call = service.getAllPosts("any");
        enqueueCall(call);
    }

    public void loadFeedTagsPosts(){

        PostService service = ServiceManager.getPostService();
        Log.d("TOOKKKK",this.getSessioToken());
        Log.d("TOOsadasdsadKKKK",this.getSessioUser());
        Call<ArrayList<Post>> call = service.getAllPosts("activity");
        enqueueCall(call);

    }

    public void loadFeedCalendarPosts(){
        PostService service = ServiceManager.getPostService();
        Log.d("TOOKKKK",this.getSessioToken());
        Log.d("TOOsadasdsadKKKK",this.getSessioUser());
        Call<ArrayList<Post>> call = service.getAllPosts("any");
        enqueueCall(call);
    }

    public void loadFeedWorkPosts () {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("work");
        enqueueCall(call);
    }

    public void loadFeedActivityPosts () {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("activity");
        enqueueCall(call);
    }

    public void loadFeedUserPosts () {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getAllPostsFromUser(this.getSessioUser());
        enqueueCall(call);
    }

    public void loadFeedHousePosts () {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("home");
        enqueueCall(call);
    }

    public void loadFeedHiddenPosts() {
        PostService service = ServiceManager.getPostService();


        Call<ArrayList<Post>> call = service.getHiddenListFromUser(this.getSessioUser());
        enqueueCall(call);
    }

    public void loadFeedAdvSearch(Bundle extras) {
        String text = extras.getString("text_to_search");
        String tipus = extras.getString("tipus");
        String lang = extras.getString("lang");
        String dateIni = extras.getString("dateIni");
        String dateFi = extras.getString("dateFi");
        String user = extras.getString("user");
        ArrayList<String> tags = extras.getStringArrayList("tags");

    }

    private void enqueueCall (Call<ArrayList<Post>> call) {
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                ControladorPresentacioAllPostsActivity.updateFeed(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
            }
        });
    }



}
