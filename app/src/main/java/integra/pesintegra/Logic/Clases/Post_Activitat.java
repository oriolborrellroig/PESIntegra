package integra.pesintegra.Logic.Clases;

import com.google.android.gms.maps.model.LatLng;

public class Post_Activitat extends Post{

    //n_max = nombre maxim de persones en aquella activitat
    private int n_max;

    public Post_Activitat(){
        super('A');
    }

    public Post_Activitat(String titol, String descripcio, String dataini, String datafi, String hora, String direccio, double lat, double lng){
        super(titol, descripcio, dataini, datafi, hora, direccio,"1",  'A', lat, lng);
        //TODO: Quan es crea un post, esta hardcodejat que l'owner es l'1
    }

    public int getN_max() {
        return n_max;
    }

    public void setN_max(int n_max) {
        this.n_max = n_max;
    }
}
