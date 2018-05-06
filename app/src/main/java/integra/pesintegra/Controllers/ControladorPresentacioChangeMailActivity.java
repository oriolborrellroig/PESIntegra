package integra.pesintegra.Controllers;

import android.content.Context;

import integra.pesintegra.Presentation.ChangeMailActivity;


public class ControladorPresentacioChangeMailActivity extends ControladorPresentacio  {

    private static ChangeMailActivity activity;
    private static ControladorDominiChangeMailActivity CDChangeMail;
    private Context context;

    public ControladorPresentacioChangeMailActivity(ChangeMailActivity allposts, Context cont) {
        this.context = cont;
        this.activity = allposts;
        this.CDChangeMail = new ControladorDominiChangeMailActivity(this);
    }

    public void changeMail (String email, String pass1) throws Exception {
        //comprova_contrasenya_coincident(pass1, pass2);
        CDChangeMail.changeMail(email, pass1);
    }
}
