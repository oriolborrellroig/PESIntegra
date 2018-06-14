package integra.pesintegra.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Sessio;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.ProfileActivity;
import integra.pesintegra.Services.ImageService;
import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiProfileActivity extends ControladorDomini {

    @SuppressLint("StaticFieldLeak")
    private static ControladorPresentacioProfileActivity Cpresentacio;

    ControladorDominiProfileActivity(ControladorPresentacioProfileActivity Cpresentacio) {
        ControladorDominiProfileActivity.Cpresentacio = Cpresentacio;
    }

    public void getUser (String id) {
        //String id = this.getSessioUser();
        UserService service = ServiceManager.getUserService();
        Call<User> createCall2 = service.getUser(id);
        createCall2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Cpresentacio.setUserInfo(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void setUserInfo(String interes, String valor, String userID) {

        //String userID = this.getSessioUser();
        UserService service = ServiceManager.getUserService();
        if (valor.equals("true")){
            super.set_tag(interes);
        }else{
            super.remove_tag(interes);
        }
        Call<JsonObject> createCall2 = service.setInterest(userID, interes, valor);
        Log.d("interiooor", "user");
        createCall2.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("crida fetaaa", response.body().get("interes").toString().replace("\"", ""));
                Cpresentacio.updateInterestInfo(response.body().get("interes").toString().replace("\"", ""),
                        response.body().get("valor").toString().replace("\"", ""));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("sadasds","aaa");
            }
        });
    }



    public String getCurrentUser() {
        return this.getSessioUser();
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
                Log.d("sadasds","aaa");
            }
        });
    }

    public void banUser(String id) {
        //TODO: canviar nom i contrassenya del user amb id = id
        Log.i("aaaa", id);
    }

    public void convertToMod(String id) {
        //TODO: canviar tipus del user a "moderador"
        Log.i("aaaa", id);
    }


    public void storeImage(ImageBM i){

        ImageService service = ServiceManager.getImageService();
        Bitmap bm = i.getBitmapImage();
        ImageBM ima = new ImageBM("12345", bm);
        //Call<Void> ccall = service.createImage(ima);
        Call<Void> ccall = service.createImage(i);
        ccall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("ole","aaa");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("sadasds","aaa");
            }
        });
    }
}
