package integra.pesintegra.Controllers;


import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;
import integra.pesintegra.Logic.Clases.Sessio;

public class ControladorDomini extends AbstractBaseController {

    private ControladorPresentacio cntrlPres;
    private Sessio usuari;

    public ControladorDomini(ControladorPresentacio controladorPresentacio) {
        super();
        this.cntrlPres = controladorPresentacio;
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
        usuari.resetSessio();
    }
}
