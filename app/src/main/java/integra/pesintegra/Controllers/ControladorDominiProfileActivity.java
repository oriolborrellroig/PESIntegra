package integra.pesintegra.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Sessio;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.ProfileActivity;
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

    public void getUser () {
        String id = this.getSessioUser();
        UserService service = ServiceManager.getUserService();
        Call<User> createCall2 = service.getUser(id);
        createCall2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("ASDAS", response.body().getId());
                Cpresentacio.setUserInfo(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("sadasds","aaa");
            }
        });
    }

    public void setUserInfo(String interes, String valor) {

        String userID = this.getSessioUser();
        UserService service = ServiceManager.getUserService();
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
}
