package integra.pesintegra.Controllers;

import android.content.Context;

import java.util.ResourceBundle;

import integra.pesintegra.Presentation.PostActivity;

public class ControladorPresentacioPostOpen extends ControladorPresentacio  {

    private PostActivity activity;
    private Context context;
    private ControladorDominiPostOpen Cdomini;

    public ControladorPresentacioPostOpen (PostActivity callActivity, Context cont) {
        this.activity = callActivity;
        this.context = cont;
        this.Cdomini = new ControladorDominiPostOpen(this);
    }


    public void deletePost (String postId) {
        Cdomini.deletePost(postId);


    }
}
