package integra.pesintegra.Controllers;

import android.content.Context;

import integra.pesintegra.Presentation.HidePostActivity;

public class ControladorPresentacioHidePosts extends ControladorPresentacio  {
    private HidePostActivity activity;
    private Context context;
    private ControladorDominiHidePosts Cdomini;

    public ControladorPresentacioHidePosts(HidePostActivity allposts, Context cont) {
        this.context = cont;
        this.activity = allposts;
        this.Cdomini = new ControladorDominiHidePosts(this);

    }
    public void loadFeedPosts () {
        Cdomini.loadFeedPosts();
    }
}
