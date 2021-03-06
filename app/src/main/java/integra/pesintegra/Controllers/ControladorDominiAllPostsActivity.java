package integra.pesintegra.Controllers;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

import integra.pesintegra.Logic.Adapter.ListAdapter;
import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Services.ImageService;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiAllPostsActivity extends ControladorDomini {
    private static ControladorPresentacioAllPostsActivity Cpresentacio;

    public void loadFeedAllBooked (String userid) {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getBookedPosts(userid);
        enqueueCall(call);
    }

    public void loadFeedAnyPosts (Integer order) {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("any", order);
        enqueueCall(call);
    }

    public void loadFeedTagsPosts(ArrayList<String> listtags){

        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getPostsByTags(listtags);
        enqueueCall(call);

    }

    public void loadFeedCalendarPosts(String id){
        Log.d("aaa", id);
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getBookedPosts(id);
        enqueueCall(call);
    }


    public void loadFeedWorkPosts (Integer order) {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("work", order);
        enqueueCall(call);
    }

    public void loadFeedActivityPosts (Integer order) {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("activity", order);
        enqueueCall(call);
    }

    public void loadFeedUserPosts (String user) {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getAllPostsFromUser(user);
        enqueueCall(call);
    }

    public void loadFeedHousePosts (Integer order) {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("home", order);
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
        PostService service = ServiceManager.getPostService();
        assert tipus != null;
        if (tipus.equals("Tots els posts") || tipus.equals("All posts") || tipus.equals("Todos los posts")) tipus = null;
        else if (tipus.equals("Activities") || tipus.equals("Activitats") || tipus.equals("Actividades")) tipus = "A";
        else if (tipus.equals("Work") || tipus.equals("Feina") || tipus.equals("Trabajo")) tipus = "F";
        else tipus = "H";
        assert lang != null;
        if (lang.equals("Qualsevol idioma") || lang.equals("Any language") || lang.equals("Cualquier idioma")) lang = null;
        else if (lang.equals("Catalan (ca)") || lang.equals("Català (ca)") || lang.equals("Catalán (ca)")) lang = "CA";
        else if (lang.equals("English (EN)") || lang.equals("Anglès (EN)") || lang.equals("Inglés (EN)")) lang = "EN";
        else lang = "ES";
        assert user != null;
        if (user.equals("")) user = null;
        assert text != null;
        if (text.equals("")) text = null;
        Call<ArrayList<Post>> call = service.advancedSearch(text, tipus, lang, dateIni, dateFi, user, tags);
        enqueueCall(call);

    }

    public void loadReportedCommentedPosts() {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getCommentedReportedPosts();
        enqueueCall(call);
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


    public void loadReportedPosts() {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getReportedPosts();
        enqueueCall(call);
    }

    public void loadTagsSessio() {
        String id = super.getSessioUser();
        UserService service = ServiceManager.getUserService();
        Call<User> createCall2 = service.getUser(id);
        createCall2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                for (int i = 0; i < response.body().getInteressos().size(); ++i) {
                    ControladorDominiAllPostsActivity.super.set_tag(response.body().getInteressos().get(i));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void setImageDrawer(ControladorPresentacioAllPostsActivity cpres) {
        Cpresentacio = cpres;
        ImageService service = ServiceManager.getImageService();
        String id = getSessioUser();
        Call<ImageBM> ccall = service.getImageProfile(id);
        ccall.enqueue(new Callback<ImageBM>() {
            @Override
            public void onResponse(Call<ImageBM> call, Response<ImageBM> response) {
                Cpresentacio.getImageResponse(response.body());
            }

            @Override
            public void onFailure(Call<ImageBM> call, Throwable t) {
            }
        });
    }

    public void posa_imatge(String id, ImageView icon_post, ListAdapter listAdapter) {
        ImageService service = ServiceManager.getImageService();
        final ImageView ff = icon_post;
        final ListAdapter list_ad2 = listAdapter;
        Call<ImageBM> ccall = service.getImagePost(id);
        ccall.enqueue(new Callback<ImageBM>() {
            @Override
            public void onResponse(Call<ImageBM> call, Response<ImageBM> response) {
                list_ad2.posa_imatge(ff, response.body().getBitmapImage());
            }

            @Override
            public void onFailure(Call<ImageBM> call, Throwable t) {
            }
        });
    }
}
