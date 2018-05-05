package integra.pesintegra.Logic.Clases;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Sessio {

    private String username;
    private List<String> tags;
    private String idioma;
    private String token;


    //creacio d'un usuari nou amb tags buids, sense foto...
    public Sessio (String username, String token) {
        this.username = username;
        this.idioma = "Catala";  //equivaldrà a numero del doc.
        this.tags = new ArrayList<>();
        this.token = token;
        Log.d("username", token);
    }



    public void resetSessio() {
        username = "";
        idioma = "";
    }


    public String getUsername() {
        return username;
    }


    public String getToken() {
        return token;
    }
}
