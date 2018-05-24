package integra.pesintegra.Controllers;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.EditActivityActivity;

public class ControladorPresentacioEditActivity extends ControladorPresentacio  {

    private EditActivityActivity activity;
    private ControladorDominiEditActivity CDEditActivity;
    private Context context;

    public ControladorPresentacioEditActivity(EditActivityActivity activity, Context cont) {
        this.context = cont;
        this.activity = activity;
        this.CDEditActivity = new ControladorDominiEditActivity(this);
    }

    public void editPost (String originPostId, Post post, Uri uri) throws Exception {
        comprovaCampNoBuid(post.getTitol());
        comprovaCampNoBuid(post.getDescripcio());
        comprovaCampNoBuid(post.getDataIni());
        comprovaCampNoBuid(post.getHora());
        comprovaCampNoBuid(post.getLocalitzacio());
        //comprovaDataValida(post.getDataFi());
        CDEditActivity.editPost(originPostId,post,uri);

    }

    public void showNewPost() {
        activity.showNewPost(context);
    }

    public LatLng getLoc(String lloc, Context context) {
        return CDEditActivity.getLoc(lloc, context);
    }
}
