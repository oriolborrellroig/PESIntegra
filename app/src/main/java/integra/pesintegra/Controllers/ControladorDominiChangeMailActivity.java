package integra.pesintegra.Controllers;

import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ControladorDominiChangeMailActivity extends ControladorDomini {


    public void changeMail (String email, String pass) {
        UserService service = ServiceManager.getUserService();
        Call<Void> createCall2 = service.updateMailUser(this.getSessioUser(), email, pass);
        createCall2.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
