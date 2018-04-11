package integra.pesintegra.Controllers;

import android.util.Log;

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

    public void creaPostActivitat(Post_Activitat activitat) {
        cntrlBD.afegeixPostActivitat(activitat);

    }

    public void creaPostHabitatge(Post_Habitatge habitatge) {
        cntrlBD.afegeixPostHabitatge(habitatge);
    }

    public void creaPostFeina(Post_Feina feina) {
        cntrlBD.afegeixPostFeina(feina);
    }
}
