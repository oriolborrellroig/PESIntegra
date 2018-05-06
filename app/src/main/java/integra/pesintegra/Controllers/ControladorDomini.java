package integra.pesintegra.Controllers;


import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;
import integra.pesintegra.Logic.Clases.Sessio;
import integra.pesintegra.Services.ServiceManager;

public class ControladorDomini extends  AbstractBaseController{

    private static ServiceManager serviceManager;
    private ControladorPresentacio CP;
    private static Sessio sessio;

    public ControladorDomini () {}
    public ControladorDomini (ControladorPresentacio cp) {
        this.CP =cp;
    }
    public void setSessio(Sessio usuari) {

        this.sessio = usuari;
         this.serviceManager = new ServiceManager(usuari.getToken(), usuari.getUsername());
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


    public ServiceManager getServiceManager() {
        return serviceManager;
    }

    protected String getSessioUser() {
        return sessio.getUsername();
    }



    protected String getSessioToken() {
        return sessio.getToken();

    }

}
