package integra.pesintegra.Logic.Clases;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Sessio {

    private String id;
    private List<String> tags;
    private String idioma;
    private String token;


    //creacio d'un usuari nou amb tags buids, sense foto...
    public Sessio (String id, String token) {
        this.id = id;
        this.idioma = "Catala";  //equivaldrà a numero del doc.
        this.tags = new ArrayList<>();
        this.token = token;
    }



    public void resetSessio() {
        id = "";
        idioma = "";
    }


    public String getUsername() {
        return id;
    }


    public String getToken() {
        return token;
    }


    /* DESCOMENTAR QUAN ESTIGUI EL ID
    public String getID() { return id; }
    */
}
