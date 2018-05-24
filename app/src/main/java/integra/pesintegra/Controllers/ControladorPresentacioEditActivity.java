package integra.pesintegra.Controllers;

import android.content.Context;
import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.CreateActivityActivity;
import integra.pesintegra.Presentation.EditActivityActivity;

public class ControladorPresentacioEditActivity extends ControladorPresentacio  {

    private EditActivityActivity activity;
    private ControladorDominiEditActivity CDCreateActivity;
    private Context context;

    public ControladorPresentacioEditActivity(EditActivityActivity activity, Context cont) {
        this.context = cont;
        this.activity = activity;
        this.CDCreateActivity = new ControladorDominiEditActivity(this);
    }

    public void createPost (Post post, Uri uri) throws Exception {
        comprovaCampNoBuid(post.getTitol());
        comprovaCampNoBuid(post.getDescripcio());
        comprovaCampNoBuid(post.getDataIni());
        comprovaCampNoBuid(post.getHora());
        comprovaCampNoBuid(post.getLocalitzacio());
        comprovaDataValida(post.getDataFi());
        CDCreateActivity.createPost(post,uri);



    }

    public void showNewPost() {
        activity.showNewPost(context);
    }

    public LatLng getLoc(String lloc, Context context) {
        return CDCreateActivity.getLoc(lloc, context);
    }
}
