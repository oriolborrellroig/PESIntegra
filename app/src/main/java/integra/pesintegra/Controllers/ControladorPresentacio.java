package integra.pesintegra.Controllers;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Sessio;

public class ControladorPresentacio extends AbstractBaseController {

    private ControladorDomini cntrlDom;

    public ControladorPresentacio() {
        super();
            cntrlDom = new ControladorDomini(this);
    }

    public void creaPost(Post post) throws Exception {
        comprovaCampNoBuid(post.getTitol());
        comprovaCampNoBuid(post.getDescripcio());
        comprovaDataNoAnterior(post.getDataFi());
        comprovaHoraValida(post.getHora());
        cntrlDom.creaPost(post);
    }

    public void createUser (String mail, String username, String pw1, String pw2, String dataNaixement) throws Exception {
        comprovaPasswordValida(pw1);
        comprovaPasswordValida(pw1);
        if (pw1 != pw2) {
            throw new Exception("Les passwords no coincideixen");
        }

    }

}
