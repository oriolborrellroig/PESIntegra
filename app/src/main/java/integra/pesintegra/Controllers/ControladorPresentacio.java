package integra.pesintegra.Controllers;

import android.util.Log;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Sessio;

public class ControladorPresentacio extends AbstractBaseController {

    private ControladorDomini cntrlDom;

    public ControladorPresentacio() {
        super();
            cntrlDom = new ControladorDomini(this);
    }

   /* public void creaPost(Post post) throws Exception {
        comprovaCampNoBuid(post.getTitol());
        comprovaCampNoBuid(post.getDescripcio());
        comprovaDataNoAnterior(post.getDataFi());
        comprovaHoraValida(post.getHora());
        cntrlDom.creaPost(post);
    }*/

    /*public void createUser (String mail, String username, String pw1, String pw2, String dataNaixement) throws Exception {
        comprovaPasswordValida(pw1);
        comprovaPasswordValida(pw1);
        if (pw1 != pw2) {
            throw new Exception("Les passwords no coincideixen");
        }

    }
    */


    //A REVISAR


    public void creaPostActivitat(String titol, String descripcio, String dataI, String dataF, String hora, String lloc) throws Exception {
        //fer comprobacions necessàries...
        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaDataValida(dataF);
        //Post_Activitat activitat = new Post_Activitat(titol, descripcio, dataI, dataF, hora, lloc);
        cntrlDom.creaPostActivitat(titol, descripcio, dataI, dataF, hora, lloc);
    }

    public void creaPostHabitatge(String titol, String descripcio, String dataI, String dataF, String hora, String lloc) throws Exception{
        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaDataValida(dataF);


        cntrlDom.creaPostHabitatge(titol, descripcio, dataI, dataF, hora, lloc);
    }

    public void creaPostFeina(String titol, String descripcio, String dataI, String dataF, String hora, String lloc) throws Exception {
        comprovaCampNoBuid(titol);
        comprovaCampNoBuid(descripcio);
        comprovaCampNoBuid(dataI);
        comprovaCampNoBuid(hora);
        comprovaCampNoBuid(lloc);
        comprovaDataValida(dataF);


        cntrlDom.creaPostFeina(titol, descripcio, dataI, dataF, hora, lloc);
    }


}
