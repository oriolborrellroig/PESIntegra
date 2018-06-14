package integra.pesintegra.Controllers;


import android.graphics.Bitmap;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;
import integra.pesintegra.Logic.Clases.Sessio;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Services.ImageService;
import integra.pesintegra.Services.ServiceManager;
import integra.pesintegra.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDomini extends  AbstractBaseController{

    private static ServiceManager serviceManager;
    private static Sessio sessio;
    public  ImageBM result;

    public ControladorDomini () {  }

    void setSessio(Sessio usuari) {

        sessio = usuari;
        serviceManager = new ServiceManager(usuari.getToken(), usuari.getUsername());
    }

    public void creaPostActivitat(Post_Activitat activitat) {

    }

    public void creaPostHabitatge(Post_Habitatge habitatge) {
    }

    public void creaPostFeina(Post_Feina feina) {
    }


    // Idea és passar tots els post de la database per comprobar quins s'haurien de guardar com expired. (es podria passar com a llista)
    public Boolean post_Caducat(Post post) {
        return comprovaDataExpired(post.getDataFi());
    }

    public void logout() {
        sessio.resetSessio();
    }


    ServiceManager getServiceManager() {
        return serviceManager;
    }

    String getSessioUser() {
        return sessio.getUsername();
    }

    public List<String> getTagsSessio(){
        return sessio.getTagsSessio();
    }

    public void set_tag(String tag){
        sessio.setTag(tag);
    }

    public void remove_tag(String tag){
        sessio.remove_tag(tag);
    }



    String getSessioToken() {
        return sessio.getToken();

    }


}
