package integra.pesintegra.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import integra.pesintegra.Presentation.LoginActivity;

public class ControladorPresentacioLoginActivity extends ControladorPresentacio  {


    private static LoginActivity activity;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @SuppressLint("StaticFieldLeak")
    private static ControladorDominiLoginActivity Cdomini;

    public ControladorPresentacioLoginActivity () {

    }

    public ControladorPresentacioLoginActivity(LoginActivity loginActivity, Context cont) {
        context = cont;
        activity = loginActivity;
        Cdomini = new ControladorDominiLoginActivity(this);
    }
    public void checkLogin (String username, String password) {
        Cdomini.checkLogin(username,hash_password(password));
    }

    public void acceptLogin() {
        activity.acceptLogin(context);
    }

    public void rejectLogin() {
        activity.rejectLogin(context);
    }

    public static String getUserSessio(){return Cdomini.getUserSession();}

    public static LoginActivity getActivityLogin(){
        return activity;
    }
}
