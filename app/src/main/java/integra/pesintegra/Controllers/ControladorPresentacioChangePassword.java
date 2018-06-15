package integra.pesintegra.Controllers;

import integra.pesintegra.Presentation.ChangePasswordActivity;
import android.content.Context;

public class ControladorPresentacioChangePassword extends ControladorPresentacio {
    private static ControladorDominiChangePassword CDChangePassword;
    private static ChangePasswordActivity activity;

    public ControladorPresentacioChangePassword(ChangePasswordActivity callActivity){
        this.activity = callActivity;
        CDChangePassword= new ControladorDominiChangePassword();
    }


    public void changePassword(String current_pass, String new_pass, String new_pass_confirm) throws Exception{
        comprova_contrasenya_coincident(new_pass, new_pass_confirm);
        CDChangePassword.canviar_contrasenya(current_pass, new_pass);
    }

    public static void answer(int code) {
        activity.changedMessage(code);
    }

}
