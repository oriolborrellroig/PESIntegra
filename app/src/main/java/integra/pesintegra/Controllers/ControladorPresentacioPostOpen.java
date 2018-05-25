package integra.pesintegra.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;

import integra.pesintegra.Logic.Clases.Comentari;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Presentation.PostActivity;

public class ControladorPresentacioPostOpen extends ControladorPresentacio  {

    private PostActivity activity;
    @SuppressLint("StaticFieldLeak")
    private static ControladorDominiPostOpen Cdomini;

    public ControladorPresentacioPostOpen (PostActivity callActivity, Context cont) {
        this.activity = callActivity;
        Cdomini = new ControladorDominiPostOpen(this);
    }

    public void deleteComment(String post_id, String comment_id){
        Cdomini.deleteComment(post_id, comment_id);
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

    public void joinActivity(Post_Activitat activity) {
        Cdomini.joinActivity(activity);
    }

    public Comentari creaComentari(String text, String data, String post_id){
        //Cdomini.creaComentari(text, data, post_id);
        String id = Cdomini.getCurrentUser();
        Comentari nou_comentari = new Comentari(id, text, data, post_id);
        Cdomini.addComentari(post_id, nou_comentari);
        return nou_comentari;
    }

    public String getCurrentUser() {
        return Cdomini.getCurrentUser();
    }

    public void isHidden(String post_id) {
        Cdomini.isHidden(post_id);
    }

    public void isHiddenCallback(Boolean hidden) {
        activity.setHidden(hidden);
    }

    public void getUserRating(String postid, String userid) {
        Cdomini.getUserRating(postid, userid);
    }

    public void setUserRating(String body) {
        activity.setUserRating(body);
    }

    public void userAssisteix(String post_id, String current_user) {
        Cdomini.userAssisteix(post_id, current_user);
    }

    public void setAssisteix(String body) {
        activity.userAssisteix(body);
    }

    public void addAttendant(String post_id, String current_user) {
        Cdomini.addAttendant(post_id, current_user);
    }

    public void removeAttendant(String post_id, String current_user) {
        Cdomini.removeAttendant(post_id, current_user);
    }

    public void setAssistents(Integer body) {
        activity.setAssistents(body);
    }
}
