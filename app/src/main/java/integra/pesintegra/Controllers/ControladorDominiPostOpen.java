package integra.pesintegra.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Comentari;
import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.PostActivity;
import integra.pesintegra.Services.ImageService;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiPostOpen extends ControladorDomini {
    private PostActivity activity;
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private static ControladorPresentacioPostOpen Cpresentacio;

    ControladorDominiPostOpen(ControladorPresentacioPostOpen cPresentacio) {
        Cpresentacio = cPresentacio;
    }


    public void deletePost (String postId) {
        PostService service = ServiceManager.getPostService();
        Call<Void> createCall = service.deletePost(postId);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void updateAddToHide ( String postId) {

        String userId = this.getSessioUser();
        UserService service = ServiceManager.getUserService();
        Call<Void> createCall = service.updateAddToHide(userId, postId);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void updateRemoveToHide (String postId) {
        String userId = this.getSessioUser();

        UserService service = ServiceManager.getUserService();
        Call<Void> createCall = service.updateRemoveToHide(userId, postId);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void addComentari (String postId, Comentari comentari) {

        PostService service = ServiceManager.getPostService();
        Call<Void> createCall = service.createComment(postId, comentari);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void deleteComment (String postId, String commentId) {

        PostService service = ServiceManager.getPostService();
        Call<Void> createCall = service.deleteComment(postId, commentId);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void getRating (String postId) {
        PostService service = ServiceManager.getPostService();
        Call<JsonObject> createCall = service.getPostRating(postId);
        createCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Cpresentacio.updateRating(response.body().get("puntuacio").toString().replace("\"", ""),
                        response.body().get("nombreVots").toString().replace("\"", ""));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void votaPost (String postId, String puntuacio) {
        PostService service = ServiceManager.getPostService();
        Call<JsonObject> createCall = service.votePost(postId, puntuacio);
        createCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Cpresentacio.updateRating(response.body().get("puntuacio").toString().replace("\"", ""),
                        response.body().get("nombreVots").toString().replace("\"", ""));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void getUserRating (String postid, String userid) {
        PostService service = ServiceManager.getPostService();
        Call<String> createCall = service.getUserVote(postid, userid);
        createCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                 Cpresentacio.setUserRating(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    public void joinActivity(Post_Activitat activity) {

    }

    public void creaComentari(String text, String data, String post_id){
        String id = this.getSessioUser();
        Comentari nou_comentari = new Comentari(id, text, data, post_id);

    }

    public String getCurrentUser() {
        return this.getSessioUser();
    }



    public void isHidden(final String post_id) {
        PostService service = ServiceManager.getPostService();
        Call<ArrayList<Post>> call = service.getHiddenListFromUser(this.getSessioUser());
        final Boolean[] b = new Boolean[1];
        b[0] = false;
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                for(int i = 0; i < response.body().size() && !b[0]; ++i){
                    if(response.body().get(i).getId().equals(post_id)) {
                        b[0] = true;
                        Cpresentacio.isHiddenCallback(b[0]);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
            }
        });
    }

    public void userAssisteix(String post_id, String current_user) {
        PostService service = ServiceManager.getPostService();
        Call<String> createCall = service.userAssisteix(post_id, current_user);
        createCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Cpresentacio.setAssisteix(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void addAttendant(String post_id, String current_user) {
        PostService service = ServiceManager.getPostService();
        Call<Integer> createCall = service.addAttendant(post_id, current_user);
        createCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Cpresentacio.setAssistents(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
            }
        });
    }

    public void removeAttendant(String post_id, String current_user) {
        PostService service = ServiceManager.getPostService();
        Call<Integer> createCall = service.removeAttendant(post_id, current_user);
        createCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Cpresentacio.setAssistents(response.body());

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void report_post(String current_user, String post_id) {
        PostService service = ServiceManager.getPostService();
        Call<Void> createCall = service.reportPost(post_id, current_user);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Cpresentacio.isReportedCallback(true);

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    public void isReported(String current_user, String post_id) {
        PostService service = ServiceManager.getPostService();
        Call<String> createCall = service.isReported(post_id, current_user);
        createCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().equals("True")) Cpresentacio.isReportedCallback(true);
                else Cpresentacio.isReportedCallback(false);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void rejectReportedPost(String postid) {
        PostService service = ServiceManager.getPostService();
        Call<Void> createCall = service.rejectReportPost(postid);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
               //TODO: Ja esta el update fet, no se que voleu que fagi, fet per Oriol Borrell.
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void reportComment(String postid, String commentid, String userid) {
        PostService service = ServiceManager.getPostService();
        Call<Void> createCall = service.reportComment(postid, commentid, userid);
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //TODO: Ja esta el report comment fet, no se que voleu que fagi, fet per Oriol Borrell. jeje xd salu2
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public ImageBM getImage(String id){

        ImageService service = ServiceManager.getImageService();
        Call<ImageBM> ccall = service.getImagePost(id);
        ccall.enqueue(new Callback<ImageBM>() {
            @Override
            public void onResponse(Call<ImageBM> call, Response<ImageBM> response) {
                Log.d("image size", ((Integer)response.body().getImageString().length()).toString());
                Cpresentacio.getImageResponse(response.body());
            }

            @Override
            public void onFailure(Call<ImageBM> call, Throwable t) {
            }
        });
        return result;
    }

    public void getPost(String post_id) {
        PostService service = ServiceManager.getPostService();
        Call<Post> createCall = service.getPost(post_id);
        createCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.d("aaa", "hola");
                Log.d("aaa", response.body().getId());
                Log.d("aaa", Integer.toString(response.body().getN_assistents()));
                Log.d("aaa", Integer.toString(response.body().getAssistentsMax()));
                Cpresentacio.loadPost(response.body());

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        });
    }

    public void isMod(String id) {
        UserService service = ServiceManager.getUserService();
        Call<User> createCall2 = service.getUser(id);
        createCall2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Cpresentacio.isModCallback(response.body().getTipus());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
