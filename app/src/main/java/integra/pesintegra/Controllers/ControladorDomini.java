package integra.pesintegra.Controllers;

import java.util.List;

import integra.pesintegra.Logic.Clases.ImageBM;

import integra.pesintegra.Logic.Clases.Sessio;


import integra.pesintegra.Services.ServiceManager;


public class ControladorDomini extends  AbstractBaseController{

    private static ServiceManager serviceManager;
    private static Sessio sessio;
    public  ImageBM result;


    public ControladorDomini () {  }

    void setSessio(Sessio usuari) {

        sessio = usuari;
        serviceManager = new ServiceManager(usuari.getToken(), usuari.getUsername());
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
