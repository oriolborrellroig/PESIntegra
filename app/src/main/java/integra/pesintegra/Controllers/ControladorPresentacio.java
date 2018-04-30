package integra.pesintegra.Controllers;

import android.app.Application;
import android.content.Context;

import java.util.Calendar;
import java.util.regex.Pattern;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;
import integra.pesintegra.Presentation.LoginActivity;
import integra.pesintegra.Controllers.ControladorServeisLoginActivity;

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

    public void comprova_login(LoginActivity la, String username, String pass){
        ControladorServeisLoginActivity controlador = new ControladorServeisLoginActivity(la, context.getContext());
        controlador.checkLogin(username, pass);
    }

    public boolean comprovar_camps(String pass1, String pass2, String email, String dataN){
        boolean mail = valid_mail(email);
        boolean pass = contrassenya_no_coincident(pass1, pass2);
        boolean data = data_naix_posterior_actual(dataN);

        return mail & !pass & !data;
    }



    public static boolean valid_mail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public boolean contrassenya_no_coincident(String pass1, String pass2){
        return !pass1.equals(pass2);
    }


    public boolean data_naix_posterior_actual(String dataN) {


        String[] parts = dataN.split("/");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        int dia_i = Integer.parseInt(part1);
        int mes_i = Integer.parseInt(part2);
        int any_i = Integer.parseInt(part3);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        boolean result = false;
        if (any_i > year) result = true;
        else if (any_i == year){
            if (mes_i > month) result = true;
            else if (mes_i == month){
                if (dia_i > day) result = true;
            }
        }

        return result;
    }


}
