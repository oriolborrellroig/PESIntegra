package integra.pesintegra.Logic.Clases;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Post_Habitatge extends Post {

    public Post_Habitatge(){
        super('H');
    }

    public Post_Habitatge(String titol, String descripcio, String dataini, String datafi, String hora, String direccio, double lat, double lng, String lang, ArrayList<String> clicked_tags){
        super(titol, descripcio, dataini, datafi, hora, direccio, "1", 'H', lat, lng, lang, clicked_tags);
        //TODO: Quan es crea un post, esta hardcodejat que l'owner es l'1
    }
}
