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

    private LoginActivity activity;
    private Context context;

    public ControladorServeisLoginActivity(LoginActivity loginActivity, Context cont) {
        this.context = cont;
        this.activity = loginActivity;
    }
    public void checkLogin (String username, String password) {
        UserService service = this.getServiceManager().getUserService();
        Call<User> createCall2 = service.loginUser(username, password);
        createCall2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //activity.updateFeed(response.body(), context);
                if ( response.body() == null) {
                    activity.rejectLogin(context);
                }
                else {
                    activity.acceptLogin(context);
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.v("TAAAAG", "Failure");
            }
        });
    }
}

