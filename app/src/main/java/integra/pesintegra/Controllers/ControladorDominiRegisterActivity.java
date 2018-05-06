package integra.pesintegra.Controllers;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import integra.pesintegra.Logic.Clases.Sessio;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.RegisterActivity;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiRegisterActivity extends ControladorDomini {


    private ControladorPresentacioRegisterActivity Cpresentacio;

    public ControladorDominiRegisterActivity(ControladorPresentacioRegisterActivity Cpresentacio) {
        this.Cpresentacio = Cpresentacio;
    }
    public void doRegister(final User user, final String hash) {
        UserService service = this.getServiceManager().getLoginService();
        Call<Void> createCall2 = service.createUser(user,hash);
        createCall2.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                UserService service = getServiceManager().getLoginService();
                Call<JsonObject> createCall2 = service.loginUser(user.getUsername(),hash);
                createCall2.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        //activity.updateFeed(response.body(), context);
                        if ( response.body() == null) {
                        }
                        else {

                            String token = response.body().get("token").toString().replace("\"", "");
                            String uid = response.body().get("user").toString().replace("\"", "");
                            createSession(token,uid);

                            Cpresentacio.acceptLogin();
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.v("TAAAAG", "Failure");
                    }
                });

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //Log.v("TAAAAG", "Failure");
            }
        });
    }

    private void createSession (String token, String username) {
        Sessio sessio = new Sessio(username,token);
        this.setSessio(sessio);

    }
}
