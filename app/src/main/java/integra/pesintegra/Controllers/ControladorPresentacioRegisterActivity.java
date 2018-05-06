package integra.pesintegra.Controllers;

import android.content.Context;

import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.RegisterActivity;

public class ControladorPresentacioRegisterActivity extends ControladorPresentacio  {

    private RegisterActivity activity;
    private Context context;
    private ControladorDominiRegisterActivity Cdomini;

    public ControladorPresentacioRegisterActivity(RegisterActivity registerActivity, Context cont) {
        this.context = cont;
        this.activity = registerActivity;
        this.Cdomini = new ControladorDominiRegisterActivity (this);
    }
    public void doRegister(User user, String hash) {
        Cdomini.doRegister(user, hash);
    }

    public void logIn (){
        activity.logIn(context);
    }

    public void acceptLogin() {
        activity.logIn(context);
    }
}
