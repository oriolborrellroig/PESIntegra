package integra.pesintegra.Controllers;

import android.util.Log;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiMapsActivity extends ControladorDomini{
    private ControladorPresentacioMapsActivity cp;

    ControladorDominiMapsActivity(ControladorPresentacioMapsActivity cp) {
        this.cp = cp;
    }

    public void getPosts(String id){
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getBookedPosts(id);
        enqueueCall(call);
    }

    private void enqueueCall (Call<ArrayList<Post>> call) {
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                cp.setPosts(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
            }
        });
    }

}
