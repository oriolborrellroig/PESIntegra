package integra.pesintegra.Controllers;

import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
public class ControladorServeisChangeMailActivity extends ControladorServeis {
    private ChangeMailActivity activity;
    private Context context;

    public ControladorServeisChangeMailActivity(ChangeMailActivity allposts, Context cont) {
        this.context = cont;
        this.activity = allposts;
*/
public class ControladorDominiChangeMailActivity extends ControladorDomini {
    private ControladorPresentacioChangeMailActivity Cpresentacio;

    public ControladorDominiChangeMailActivity(ControladorPresentacioChangeMailActivity cp) {
        this.Cpresentacio = cp;
}

    public void changeMail (String email, String pass) {
        UserService service = this.getServiceManager().getUserService();
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
