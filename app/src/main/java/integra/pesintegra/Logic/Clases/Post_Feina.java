package integra.pesintegra.Logic.Clases;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Post_Feina extends Post {

    public Post_Feina(){
        super('F');
    }

    public Post_Feina(String titol, String descripcio, String dataini, String datafi, String hora, String direccio, double lat, double lng, String lang, ArrayList<String> clicked_tags){
        super(titol, descripcio, dataini, datafi, hora, direccio, "1", 'F', lat, lng, lang, clicked_tags);
    }

}
