package integra.pesintegra.Controllers;


import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;
import integra.pesintegra.Logic.Clases.Sessio;
import integra.pesintegra.Services.ServiceManager;

public class ControladorDomini extends  AbstractBaseController{

    private static ServiceManager serviceManager;
    private static Sessio sessio;

    public ControladorDomini () {}

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


    String getSessioToken() {
        return sessio.getToken();

    }

}
