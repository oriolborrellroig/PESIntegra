package integra.pesintegra.Controllers;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Sessio;
import integra.pesintegra.Services.UserService;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiLoginActivity extends ControladorDomini {

    private ControladorPresentacioLoginActivity Cpresentasio;
    private Context context;
    private String username;

    public ControladorDominiLoginActivity(ControladorPresentacioLoginActivity Cpresentasio) {
        this.Cpresentasio = Cpresentasio;
    }
    public void checkLogin (String username, String password) {
        UserService service = this.getServiceManager().getLoginService();
        Call<JsonObject> createCall2 = service.loginUser(username, password);
        createCall2.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //activity.updateFeed(response.body(), context);
                if ( response.body() == null) {
                    Cpresentasio.rejectLogin();
                }
                else {
                    createSession(response.body().get("token").toString().replace("\"", ""),
                            response.body().get("user").toString().replace("\"", "")
                            );
                    Cpresentasio.acceptLogin();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.v("TAAAAG", "Failure");
            }
        });
    }

    private void createSession (String token, String username) {
        Sessio sessio = new Sessio(username,token);
        this.setSessio(sessio);

    }
}

