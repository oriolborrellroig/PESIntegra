package integra.pesintegra.Controllers;

import android.content.Context;

import java.util.ResourceBundle;

import integra.pesintegra.Presentation.PostActivity;

public class ControladorPresentacioPostOpen extends ControladorPresentacio  {

    private PostActivity activity;
    private Context context;
    private static ControladorDominiPostOpen Cdomini;

    public ControladorPresentacioPostOpen (PostActivity callActivity, Context cont) {
        this.activity = callActivity;
        this.context = cont;
        this.Cdomini = new ControladorDominiPostOpen(this);
    }


    public static void deletePost (String postId) {
        Cdomini.deletePost(postId);
    }

    public static void updateAddHiddenList(String postId, String userId){
        Cdomini.updateAddToHide(userId, postId);
    }

    public static void updateRemoveHiddenList(String postId, String userId){
        Cdomini.updateRemoveToHide(userId, postId);
    }

}
