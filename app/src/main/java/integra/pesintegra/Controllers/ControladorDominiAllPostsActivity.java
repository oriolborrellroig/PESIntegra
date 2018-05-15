package integra.pesintegra.Controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Presentation.LoginActivity;
import integra.pesintegra.Services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiAllPostsActivity extends ControladorDomini {

    private ControladorPresentacioAllPostsActivity Cpresentacio;
    private Context context;
    private boolean h;
    private boolean tags;

    public ControladorDominiAllPostsActivity(ControladorPresentacioAllPostsActivity allposts) {
        this.Cpresentacio = allposts;
        h = false;
        tags = false;
    }

    public void loadFeedAnyPosts () {
        PostService service = this.getServiceManager().getPostService();
        Log.d("TOOKKKK",this.getSessioToken());
        Log.d("TOOsadasdsadKKKK",this.getSessioUser());
        Call<ArrayList<Post>> call = service.getAllPosts("any");
        enqueueCall(call);
    }

    public void loadFeedTagsPosts(){
        tags = true;
        PostService service = this.getServiceManager().getPostService();
        Log.d("TOOKKKK",this.getSessioToken());
        Log.d("TOOsadasdsadKKKK",this.getSessioUser());
        Call<ArrayList<Post>> call = service.getAllPosts("any");
        enqueueCall(call);

    }

    public void loadFeedWorkPosts () {
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("work");
        enqueueCall(call);
    }

    public void loadFeedActivityPosts () {
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("activity");
        enqueueCall(call);
    }

    public void loadFeedUserPosts () {
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> call = service.getAllPostsFromUser(this.getSessioUser());
        enqueueCall(call);
    }

    public void loadFeedHousePosts () {
        PostService service = this.getServiceManager().getPostService();
        Call<ArrayList<Post>> call = service.getAllPosts("home");
        enqueueCall(call);
    }

    public void loadFeedHiddenPosts() {
        PostService service = this.getServiceManager().getPostService();


        Call<ArrayList<Post>> call = service.getHiddenListFromUser(this.getSessioUser());
        h = true;
        enqueueCall(call);
    }

    private void enqueueCall (Call<ArrayList<Post>> call) {
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                //aixo esta fet aixi cutre per lo dels tags, cal mirar en lloc del tipus que els tags del post coincideixin amb els de l'usuari loggejat

                if (tags){
                    ArrayList aa = new ArrayList<Post>();
                    for (int i = 0; i < response.body().size(); i++){
                            if (response.body().get(i).getTipus() == 'A'){
                                aa.add(response.body().get(i));
                            }
                        }
                    Cpresentacio.updateFeed(aa);
                }else{
                    Cpresentacio.updateFeed(response.body());

                }

            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
            }
        });
    }


}
