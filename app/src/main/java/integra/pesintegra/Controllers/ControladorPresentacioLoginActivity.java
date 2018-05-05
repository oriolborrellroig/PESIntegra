package integra.pesintegra.Controllers;

import android.content.Context;
import android.util.Log;

import integra.pesintegra.Presentation.LoginActivity;

public class ControladorPresentacioLoginActivity extends ControladorPresentacio  {


    private LoginActivity activity;
    private Context context;
    private ControladorDominiLoginActivity Cdomini;

    public ControladorPresentacioLoginActivity(LoginActivity loginActivity, Context cont) {
        this.context = cont;
        this.activity = loginActivity;
        this.Cdomini = new ControladorDominiLoginActivity(this);
    }
    public void checkLogin (String username, String password) {
        Cdomini.checkLogin(username,password);
    }

    public void acceptLogin() {
        activity.acceptLogin(context);
    }

    public void rejectLogin() {
        activity.rejectLogin(context);
    }
}
