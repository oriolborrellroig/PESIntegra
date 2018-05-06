package integra.pesintegra.Controllers;

import integra.pesintegra.Presentation.ChangeMailActivity;
import integra.pesintegra.Presentation.ChangePasswordActivity;
import android.content.Context;
import integra.pesintegra.Presentation.ChangeMailActivity;

public class ControladorPresentacioChangePassword extends ControladorPresentacio {
    private static ChangePasswordActivity activity;
    private static ControladorDominiChangePassword CDChangePassword;
    private Context context;

    public ControladorPresentacioChangePassword(ChangePasswordActivity act, Context con){
        this.activity =act;
        this.CDChangePassword= new ControladorDominiChangePassword(this);
        this.context = con;

    }

    public void changePassword(String userID, String current_pass, String new_pass, String new_pass_confirm) throws Exception{
        comprova_contrasenya_coincident(new_pass, new_pass_confirm);
        CDChangePassword.canviar_contrasenya(userID, current_pass, new_pass);
    }

}
