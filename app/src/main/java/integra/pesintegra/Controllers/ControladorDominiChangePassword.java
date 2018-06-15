package integra.pesintegra.Controllers;

import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;


public class ControladorDominiChangePassword extends ControladorDomini {

    public void canviar_contrasenya(String userID, String current_pass, String new_pass){
        UserService service = ServiceManager.getUserService();
        Call<Void> createCall2 = service.updatePasswordUser(current_pass, new_pass);
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
