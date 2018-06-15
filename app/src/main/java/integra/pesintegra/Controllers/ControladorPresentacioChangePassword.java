package integra.pesintegra.Controllers;

import integra.pesintegra.Presentation.ChangePasswordActivity;
import android.content.Context;

public class ControladorPresentacioChangePassword extends ControladorPresentacio {
    private static ControladorDominiChangePassword CDChangePassword;

    public ControladorPresentacioChangePassword(ChangePasswordActivity act, Context con){
        CDChangePassword= new ControladorDominiChangePassword();
    }

    public void changePassword(String userID, String current_pass, String new_pass, String new_pass_confirm) throws Exception{
        comprova_contrasenya_coincident(new_pass, new_pass_confirm);
        CDChangePassword.canviar_contrasenya(userID, current_pass, new_pass);
    }

}
