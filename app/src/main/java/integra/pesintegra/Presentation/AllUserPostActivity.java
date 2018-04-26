package integra.pesintegra.Presentation;

import android.content.Context;

import java.util.ArrayList;

import integra.pesintegra.Controllers.ControladorServeisAllPostUser;
import integra.pesintegra.Logic.Clases.Post;

public class AllUserPostActivity extends BaseActivity {
    //TODO: S'ha de fer tota la classe
    private void getPostsFromDB() {
        ControladorServeisAllPostUser cs = new ControladorServeisAllPostUser(this,getApplicationContext());
        cs.loadFeedPosts("1");//TODO: l'usuari esta hardcodejat com a 1, pero aqui va el nom de l'usuari (o el correu, no se)
    }

    public void updateFeed(ArrayList<Post> body, Context context) {
        //TODO: Quan volgueu agafar els post del usuari, Cridareu la funció getPostFromBD(), i aqui tindreu el resultat (veure AllPostActivity)
    }

}
