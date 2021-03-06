package integra.pesintegra.Controllers;

import android.content.Context;
import android.net.Uri;

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


    public void editPost (String originPostId, Post post, String titol, String dataF, String descripcio,
                          String hora, String lang, String n_assistents, String lloc, LatLng coord, Uri uri, char post_tipus) throws Exception {

        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataF);
        comprovaCampNoBuid(hora);
        comprovaDataValida(dataF);
        comprovaCampNoBuid(lloc);
        if (post_tipus == 'A'){
            post.setAssistentsMax(comprova_participants_to_integer(n_assistents));
        }
        post.setTitol(titol);
        post.setTDataIni(dataActual());
        post.setDataFi(dataF);
        post.setDescripcio(descripcio);
        post.setHora(hora);
        post.setIdioma(lang);
        post.setLocalitzacio(lloc);
        if(coord != null )post.setCoord(coord.latitude, coord.longitude);

        CDEditActivity.editPost(originPostId, post, uri);

    }

    public void showNewPost() {
        activity.showNewPost(context);
    }

    public LatLng getLoc(String lloc, Context context) {
        return CDEditActivity.getLoc(lloc, context);
    }
}
