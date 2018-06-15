package integra.pesintegra.Controllers;


import com.google.android.gms.maps.model.LatLng;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.Presentation.AllPostsActivity;
import integra.pesintegra.Presentation.BaseActivity;
import integra.pesintegra.Presentation.LoginActivity;

public class ControladorPresentacio extends AbstractBaseController {

    private ControladorDomini cntrlDom;
    private ApplicationContextProvider context;
    private AllPostsActivity activity;

    public ControladorPresentacio() {
        super();
            cntrlDom = new ControladorDomini();
    }



    public Post_Activitat creaPostActivitat(String titol, String descripcio, String dataI, String dataF, String hora, String lloc, LatLng coord, String lang, ArrayList<String> clicked_tags, String nmax) throws Exception {

        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaCampNoNull(coord);
        comprovaDataValida(dataF);
        Integer n_max = comprova_participants_to_integer(nmax);
        String owner = this.getSessioUser();
        Post_Activitat activitat = new Post_Activitat(titol, descripcio, dataI, dataF, hora, lloc, owner, coord.latitude, coord.longitude, lang, clicked_tags, n_max);

        return activitat;
    }

    public Post_Habitatge creaPostHabitatge(String titol, String descripcio, String dataI, String dataF, String hora, String lloc, LatLng coord, String lang, ArrayList<String> clicked_tags) throws Exception {

        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaCampNoNull(coord);
        comprovaDataValida(dataF);

        Post_Habitatge habitatge = new Post_Habitatge(titol, descripcio, dataI, dataF, hora, lloc, coord.latitude, coord.longitude, lang, clicked_tags);

        return habitatge;
    }

    public Post_Feina creaPostFeina(String titol, String descripcio, String dataI, String dataF, String hora, String lloc, LatLng coord, String lang, ArrayList<String> clicked_tags) throws Exception {

        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaCampNoNull(coord);
        comprovaDataValida(dataF);

        Post_Feina feina = new Post_Feina(titol, descripcio, dataI, dataF, hora, lloc, coord.latitude, coord.longitude, lang, clicked_tags);

        return feina;
    }

    public void logout() {
        cntrlDom.logout();
    }



    public User comprovar_camps(String pass1, String pass2, String email, String dataN, String username) throws Exception{
        valid_mail(email);
        comprovaCampNoBuid(username);
        comprova_contrasenya_coincident(pass1, pass2);
        data_naix_correcte(dataN);
        return new User(email, "usuari", dataN, username);
    }


    public String getSessioUser(){
        return cntrlDom.getSessioUser();
    }




}
