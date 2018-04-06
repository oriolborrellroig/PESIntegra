package integra.pesintegra.Logic.Clases;

import java.util.ArrayList;
import java.util.List;

public class Sessio {

    private String username;
    private String password;
    private String mail;
    private List<String> tags;
    private String idioma;

    //creacio d'un usuari nou amb tags buids, sense foto...
    public Sessio (String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.idioma = "Catala";  //equivaldrà a numero del doc.
        this.tags = new ArrayList<>();
    }

    //càrrega d'un usuari de la bd, amb els seus atributs guardats com parametres tb
    public Sessio (String username, String password) {
        this.username = username;
        this.password = password;
        /*...

         */


    }
}
