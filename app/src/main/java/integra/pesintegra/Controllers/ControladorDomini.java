package integra.pesintegra.Controllers;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;
import integra.pesintegra.Logic.Clases.Sessio;

public class ControladorDomini extends AbstractBaseController {

    private ControladorPresentacio cntrlPres;
    private ControladorBD cntrlBD;
    private Sessio usuari;

    public ControladorDomini(ControladorPresentacio controladorPresentacio) {
        super();
        this.usuari = usuari;
        this.cntrlPres = controladorPresentacio;
        this.cntrlBD = new ControladorBD();


    }

    public void creaPostActivitat(String titol, String descripcio, String dataI, String dataF, String hora, String lloc) {
        Post_Activitat activitat = new Post_Activitat(titol, descripcio, dataI, dataF, hora, lloc);
        cntrlBD.afegeixPostActivitat(activitat);
    }

    public void creaPostHabitatge(String titol, String descripcio, String dataI, String dataF, String hora, String lloc) {
        Post_Habitatge habitatge = new Post_Habitatge(titol, descripcio, dataI, dataF, hora, lloc);
        cntrlBD.afegeixPostHabitatge(habitatge);
    }

    public void creaPostFeina(String titol, String descripcio, String dataI, String dataF, String hora, String lloc) {
        Post_Feina treball = new Post_Feina(titol, descripcio, dataI, dataF, hora, lloc);
        cntrlBD.afegeixPostFeina(treball);
    }
}
