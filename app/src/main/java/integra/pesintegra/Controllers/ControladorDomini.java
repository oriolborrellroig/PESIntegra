package integra.pesintegra.Controllers;

import integra.pesintegra.Logic.Clases.Post;
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

    public void creaPost(Post post) {
        cntrlBD.creaPost();
    }
}
