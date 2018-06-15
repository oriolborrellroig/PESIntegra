package integra.pesintegra.Controllers;

import android.content.Context;

import com.google.gson.JsonObject;


import integra.pesintegra.Logic.Clases.Sessio;
import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiLoginActivity extends ControladorDomini {

    private ControladorPresentacioLoginActivity Cpresentacio;

    ControladorDominiLoginActivity(ControladorPresentacioLoginActivity Cpresentacio) {
        this.Cpresentacio = Cpresentacio;
    }
    public void checkLogin (String username, String password) {
        UserService service = ServiceManager.getLoginService();
        Call<JsonObject> createCall2 = service.loginUser(username, password);
        createCall2.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if ( response.body() == null) {
                    Cpresentacio.rejectLogin();
                }
                else {
                    createSession(response.body().get("token").toString().replace("\"", ""),
                            response.body().get("user").toString().replace("\"", ""),
                            response.body().get("tipus").toString().replace("\"", ""));
                    Cpresentacio.acceptLogin();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });
    }

    private void createSession (String token, String username, String tipus) {
        Sessio sessio = new Sessio(username,token, tipus);
        this.setSessio(sessio);

    }

    public String getUserSession(){
        return getSessioUser();
    }
}

