package integra.pesintegra.Controllers;

import android.content.Context;

import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.ProfileActivity;

public class ControladorPresentacioProfileActivity extends ControladorPresentacio  {

    private ProfileActivity activity;
    private Context context;
    private ControladorDominiProfileActivity Cdomini;

    public ControladorPresentacioProfileActivity(ProfileActivity allposts, Context cont) {
        this.context = cont;
        this.activity = allposts;
        this.Cdomini = new ControladorDominiProfileActivity (this);
    }

    public void getUser (String id) {
        Cdomini.getUser(id);
    }

    public void setUserInfo (User body) {
        activity.setUserInfo(body,context);
    }
}
