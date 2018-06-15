package integra.pesintegra.Controllers;

import android.content.Context;

import integra.pesintegra.Presentation.ChangeMailActivity;


public class ControladorPresentacioChangeMailActivity extends ControladorPresentacio  {

    private static ControladorDominiChangeMailActivity CDChangeMail;

    public ControladorPresentacioChangeMailActivity(ChangeMailActivity allposts, Context cont) {
        CDChangeMail = new ControladorDominiChangeMailActivity();
    }

    public void changeMail (String email, String pass1) throws Exception {
        CDChangeMail.changeMail(email, pass1);
    }
}
