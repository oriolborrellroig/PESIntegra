package integra.pesintegra.Controllers;

import android.content.Context;

import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.ProfileActivity;

public class ControladorPresentacioProfileActivity extends ControladorPresentacio  {

    private ProfileActivity activity;
    private Context context;
    private static ControladorDominiProfileActivity Cdomini;

    public ControladorPresentacioProfileActivity(ProfileActivity allposts, Context cont) {
        this.context = cont;
        this.activity = allposts;
        this.Cdomini = new ControladorDominiProfileActivity (this);
    }

    public static void getUser () {
        Cdomini.getUser();
    }

    public void setUserInterest (String interes, String nouValor) {
        Cdomini.setUserInfo(interes.toLowerCase(),nouValor.toLowerCase());
    }

    public void setUserInfo (User body) {
        activity.setUserInfo(body,context);
    }

    public void updateInterestInfo(String interes, String valor) {
        activity.updateInterestInfo(interes, valor);
    }
}
