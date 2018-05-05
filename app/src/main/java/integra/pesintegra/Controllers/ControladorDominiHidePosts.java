package integra.pesintegra.Controllers;

import android.content.Context;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Presentation.HidePostActivity;
import integra.pesintegra.Services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiHidePosts extends ControladorDomini{

    private ControladorPresentacioHidePosts CPresentacio;

    public ControladorDominiHidePosts(ControladorPresentacioHidePosts CPresentacio) {

        this.CPresentacio = CPresentacio;
    }
    public void loadFeedPosts () {
        PostService service = this.getServiceManager().getPostService();

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Cal canviar aquesta linea
        Call<ArrayList<Post>> createCall2 = service.getAllPosts("home");


        createCall2.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                CPresentacio.loadFeedPosts();
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
            }
        });
    }
}
