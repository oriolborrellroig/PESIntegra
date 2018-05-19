package integra.pesintegra.Logic.Clases;

import com.google.android.gms.maps.model.LatLng;

public class Post_Habitatge extends Post {

    public Post_Habitatge(){
        super('H');
    }

    public Post_Habitatge(String titol, String descripcio, String dataini, String datafi, String hora, String direccio, double lat, double lng, String lang){
        super(titol, descripcio, dataini, datafi, hora, direccio, "1", 'H', lat, lng, lang);
        //TODO: Quan es crea un post, esta hardcodejat que l'owner es l'1
    }
}
