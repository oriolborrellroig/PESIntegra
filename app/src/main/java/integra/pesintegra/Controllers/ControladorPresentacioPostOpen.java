package integra.pesintegra.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.ResourceBundle;

import integra.pesintegra.Presentation.PostActivity;

public class ControladorPresentacioPostOpen extends ControladorPresentacio  {

    private PostActivity activity;
    @SuppressLint("StaticFieldLeak")
    private static ControladorDominiPostOpen Cdomini;

    public ControladorPresentacioPostOpen (PostActivity callActivity, Context cont) {
        this.activity = callActivity;
        Cdomini = new ControladorDominiPostOpen(this);
    }


    public static void deletePost (String postId) {
        Cdomini.deletePost(postId);
    }

    public static void updateAddHiddenList(String postId, String userId){
        Cdomini.updateAddToHide(postId);
    }

    public static void updateRemoveHiddenList(String postId, String userId){
        Cdomini.updateRemoveToHide(postId);
    }


    public void getPostRating(String postId) {
        Cdomini.getRating(postId);


    }

    public void votePost(String postId, String puntuacio) {
        Cdomini.votaPost(postId, puntuacio);


    }

    public void updateRating(String puntuacio, String nombreVots) {
        activity.updateRating(puntuacio,nombreVots);
    }

    public void joinActivity() {
        Cdomini.joinActivity();
    }
}
