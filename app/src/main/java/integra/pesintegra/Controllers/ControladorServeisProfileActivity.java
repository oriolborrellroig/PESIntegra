package integra.pesintegra.Controllers;

import android.content.Context;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.ProfileActivity;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorServeisProfileActivity extends ControladorServeis {
    private ProfileActivity activity;
    private Context context;

    public ControladorServeisProfileActivity(ProfileActivity allposts, Context cont) {
        this.context = cont;
        this.activity = allposts;
    }

    public void getUser (String id) {
        UserService service = this.getServiceManager().getUserService();
        Call<User> createCall2 = service.getUser(id);
        createCall2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                activity.setUserInfo(response.body(), context);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }
}
