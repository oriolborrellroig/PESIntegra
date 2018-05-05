package integra.pesintegra.Controllers;

import android.content.Context;
import android.net.Uri;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.CreateActivityActivity;

public class ControladorPresentacioCreateActivity extends ControladorPresentacio  {

    private CreateActivityActivity activity;
    private ControladorDominiCreateActivity CDCreateActivity;
    private Context context;

    public ControladorPresentacioCreateActivity(CreateActivityActivity activity, Context cont) {
        this.context = cont;
        this.activity = activity;
        this.CDCreateActivity = new ControladorDominiCreateActivity(this);
    }

    public void createPost (Post post, Uri uri) throws Exception {
        comprovaCampNoBuid(post.getTitol());
        comprovaCampNoBuid(post.getDescripcio());
        comprovaCampNoBuid(post.getDataIni());
        comprovaCampNoBuid(post.getHora());
        comprovaCampNoBuid(post.getDireccio());
        comprovaDataValida(post.getDataFi());
        CDCreateActivity.createPost(post,uri);



    }

    public void showNewPost() {
        activity.showNewPost(context);
    }
}
