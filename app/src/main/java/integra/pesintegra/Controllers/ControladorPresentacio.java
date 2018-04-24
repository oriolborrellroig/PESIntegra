package integra.pesintegra.Controllers;


import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;

public class ControladorPresentacio extends AbstractBaseController {

    private ControladorDomini cntrlDom;

    public ControladorPresentacio() {
        super();
            cntrlDom = new ControladorDomini(this);
    }





    public Post_Activitat creaPostActivitat(String titol, String descripcio, String dataI, String dataF, String hora, String lloc) throws Exception {

        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaDataValida(dataF);

        Post_Activitat activitat = new Post_Activitat(titol, descripcio, dataI, dataF, hora, lloc);
        cntrlDom.creaPostActivitat(activitat);

        return activitat;
    }

    public Post_Habitatge creaPostHabitatge(String titol, String descripcio, String dataI, String dataF, String hora, String lloc) throws Exception {

        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaDataValida(dataF);

        Post_Habitatge habitatge = new Post_Habitatge(titol, descripcio, dataI, dataF, hora, lloc);
        cntrlDom.creaPostHabitatge(habitatge);

        return habitatge;
    }

    public Post_Feina creaPostFeina(String titol, String descripcio, String dataI, String dataF, String hora, String lloc) throws Exception {

        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaDataValida(dataF);

        Post_Feina feina = new Post_Feina(titol, descripcio, dataI, dataF, hora, lloc);
        cntrlDom.creaPostFeina(feina);

        return feina;
    }

    public void logout() {
        cntrlDom.logout();
    }

}
