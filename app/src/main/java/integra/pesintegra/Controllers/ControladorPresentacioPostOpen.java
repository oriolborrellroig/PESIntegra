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

    public void getPostRating(String postId) {
        Cdomini.getRating(postId);


    }

    public void votePost(String postId, String puntuacio) {
        Cdomini.votaPost(postId, puntuacio);


    }

    public void updateRating(String puntuacio, String nombreVots) {
        activity.updateRating(puntuacio,nombreVots);
    }
}
