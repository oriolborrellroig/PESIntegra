package integra.pesintegra.Controllers;

import android.app.Application;
import android.content.Context;

import java.util.Calendar;
import java.util.regex.Pattern;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;
import integra.pesintegra.Logic.Clases.Sessio;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.LoginActivity;

public class ControladorPresentacio extends AbstractBaseController {

    private ControladorDomini cntrlDom;
    private ApplicationContextProvider context;

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



    public User comprovar_camps(String pass1, String pass2, String email, String dataN) throws Exception{

        valid_mail(email);
        comprova_contrasenya_coincident(pass1, pass2);
        data_naix_correcte(dataN);

        return new User(email, pass1, "usuari", dataN);
    }

}
