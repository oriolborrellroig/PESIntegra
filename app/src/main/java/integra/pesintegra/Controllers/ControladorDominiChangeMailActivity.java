package integra.pesintegra.Controllers;

import android.content.Context;

import integra.pesintegra.Presentation.ChangeMailActivity;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiChangeMailActivity extends ControladorDomini {
    private ControladorPresentacioChangeMailActivity Cpresentacio;

    public ControladorDominiChangeMailActivity(ControladorPresentacioChangeMailActivity cp) {
        this.Cpresentacio = cp;
    }

    public void changeMail (String id, String newmail) {
        UserService service = this.getServiceManager().getUserService();
        Call<Void> createCall2 = service.updateMailUser(id, newmail);
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