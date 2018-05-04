package integra.pesintegra.Controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import integra.pesintegra.Services.UserService;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorServeisLoginActivity extends ControladorServeis {

    private static LoginActivity activity;
    private static Context context;

    public ControladorServeisLoginActivity(LoginActivity loginActivity, Context cont) {
        this.context = cont;
        this.activity = loginActivity;
    }
    public static void checkLogin (final String username, String password) {
        UserService service = getServiceManager().getUserService();
        Call<User> createCall2 = service.getUser(username, password);
        createCall2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //activity.updateFeed(response.body(), context);
                if ( response.body() == null) {
                    activity.rejectLogin(context);
                }
                else {
                    activity.acceptLogin(context, response.body());
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.v("TAAAAG", "Failure");
            }
        });
    }
}

