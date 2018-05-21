package integra.pesintegra.Controllers;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            cntrlDom = new ControladorDomini();
    }



    public Post_Activitat creaPostActivitat(String titol, String descripcio, String dataI, String dataF, String hora, String lloc, LatLng coord, String lang) throws Exception {

        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaCampNoNull(coord);
        comprovaDataValida(dataF);

        Post_Activitat activitat = new Post_Activitat(titol, descripcio, dataI, dataF, hora, lloc, coord.latitude, coord.longitude, lang);
        cntrlDom.creaPostActivitat(activitat);

        return activitat;
    }

    public Post_Habitatge creaPostHabitatge(String titol, String descripcio, String dataI, String dataF, String hora, String lloc, LatLng coord, String lang) throws Exception {

        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaCampNoNull(coord);
        comprovaDataValida(dataF);

        Post_Habitatge habitatge = new Post_Habitatge(titol, descripcio, dataI, dataF, hora, lloc, coord.latitude, coord.longitude, lang);
        cntrlDom.creaPostHabitatge(habitatge);

        return habitatge;
    }

    public Post_Feina creaPostFeina(String titol, String descripcio, String dataI, String dataF, String hora, String lloc, LatLng coord, String lang) throws Exception {

        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaCampNoNull(coord);
        comprovaDataValida(dataF);

        Post_Feina feina = new Post_Feina(titol, descripcio, dataI, dataF, hora, lloc, coord.latitude, coord.longitude, lang);
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
        return new User(email, "usuari", dataN);
    }

    public String hash_password(String pas) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pas.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public String getSessioUser(){
        return cntrlDom.getSessioUser();
    }


}
