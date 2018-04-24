package integra.pesintegra.Controllers;

import android.content.Context;
import android.util.Log;

import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.RegisterActivity;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorServeisRegisterActivity extends ControladorServeis {

    private RegisterActivity activity;
    private Context context;

    public ControladorServeisRegisterActivity(RegisterActivity registerActivity, Context cont) {
        this.context = cont;
        this.activity = registerActivity;
    }
    public void doRegister (User user) {
        UserService service = this.getServiceManager().getUserService();
        Call<Void> createCall2 = service.createUser(user);
        createCall2.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //activity.updateFeed(response.body(), context);
                activity.logIn(context);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //Log.v("TAAAAG", "Failure");
            }
        });
    }
}
