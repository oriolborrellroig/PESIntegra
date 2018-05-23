package integra.pesintegra.Controllers;

import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiSearchUserActivity extends ControladorDomini {
    private ControladorPresentacioSearchUserActivity cp;

    ControladorDominiSearchUserActivity(ControladorPresentacioSearchUserActivity cp){
        this.cp = cp;
    }

    public void getSearchUser(String user) {
        //Aqui va la crida a BD
        UserService service = ServiceManager.getUserService();
        Call<User> call = service.getUserByUsername(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body() != null) cp.setId(response.body().getId());
                else cp.setId("null");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });

    }
}
