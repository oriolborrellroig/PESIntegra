package integra.pesintegra.Controllers;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.JsonObject;


import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.User;
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

        createCall2.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                Cpresentacio.updateInterestInfo(response.body().get("interes").toString().replace("\"", ""),
                        response.body().get("valor").toString().replace("\"", ""));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

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

            }
        });
    }

    public void unbanUser(String id) {
        UserService service = ServiceManager.getUserService();
        Call<Void> createCall2 = service.unbanUser(id);
        createCall2.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void banUser(String id) {
        UserService service = ServiceManager.getUserService();
        Call<Void> createCall2 = service.banUser(id);
        createCall2.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void convertToMod(String id) {
        UserService service = ServiceManager.getUserService();
        Call<Void> createCall2 = service.createMod(id);
        createCall2.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    public void storeImage(ImageBM i){
        ImageService service = ServiceManager.getImageService();
        Bitmap bm = i.getBitmapImage();
        ImageBM ima = new ImageBM("12345", bm);
        Call<Void> ccall = service.createImagePost(i);
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

    public ImageBM getImage(String id){

        ImageService service = ServiceManager.getImageService();
        Call<ImageBM> ccall = service.getImageProfile(id);
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

    public void getProfileTipus(String id) {
        UserService service = ServiceManager.getUserService();
        Call<User> createCall2 = service.getUser(id);
        createCall2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Cpresentacio.getProfileTipusCallback(response.body().getTipus());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
