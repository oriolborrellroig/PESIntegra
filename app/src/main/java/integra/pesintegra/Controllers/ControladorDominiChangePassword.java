package integra.pesintegra.Controllers;

import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiChangePassword extends ControladorDomini {

    public void canviar_contrasenya(String userID, String current_pass, String new_pass){
        UserService service = ServiceManager.getUserService();
        service.updatePasswordUser(userID, new_pass);
        //TODO:funcio per canviar la contrasenya a la BD.
    }
}
