package integra.pesintegra.Controllers;

import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiChangePassword extends ControladorDomini {

    private static ControladorPresentacioChangePassword CPChangePassword;

    public ControladorDominiChangePassword (ControladorPresentacioChangePassword cpc) {
        CPChangePassword = cpc;
    }

    public void canviar_contrasenya(String userID, String current_pass, String new_pass){
        UserService service = this.getServiceManager().getUserService();
        //TODO:funcio per canviar la contrasenya a la BD.
    }
}
