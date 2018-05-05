package integra.pesintegra.Controllers;

import android.content.Context;

import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Presentation.ChangeProfileActivity;


public class ControladorPresentacioChangeMailActivity extends ControladorPresentacio  {

    private ChangeProfileActivity activity;
    private ControladorDominiChangeMailActivity CDChangeMail;
    private Context context;

    public ControladorPresentacioChangeMailActivity(ChangeProfileActivity allposts, Context cont) {
        this.context = cont;
        this.activity = allposts;
        this.CDChangeMail = new ControladorDominiChangeMailActivity(this);
    }

    public void changeMail (String id, String newmail) {
        CDChangeMail.changeMail(id,newmail);
    }
}
