package integra.pesintegra.Logic.Clases;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Sessio {

    private String id;
    private List<String> tags;
    private String idioma;
    private String token;
    private String tipusUser;


    //creacio d'un usuari nou amb tags buids, sense foto...
    public Sessio (String id, String token, String tipus) {
        this.id = id;
        this.idioma = "Catala";  //equivaldrà a numero del doc.
        this.tags = new ArrayList<>();
        this.token = token;
        this.tipusUser = tipus;
    }



    public void resetSessio() {
        id = "";
        idioma = "";
    }


    public String getUsername() {
        return id;
    }

    public List<String> getTagsSessio(){
        return tags;
    }


    public String getToken() {
        return token;
    }

    public void setTag(String tag){
        tags.add(tag);
    }
    public void remove_tag(String tag){
        tags.remove(tag);
    }


}
