package integra.pesintegra.Logic.Clases;


import java.util.ArrayList;
import java.util.List;

public class Post_Activitat extends Post{

    //n_max = nombre maxim de persones en aquella activitat
    private int n_max;
    //n_act = nombre de persones apuntades actualment a aquella activitat
    private int n_act;

    public Post_Activitat(){
        super('A');
    }

    public Post_Activitat(String titol, String descripcio, String dataini, String datafi, String hora, String direccio, double lat, double lng, String lang, ArrayList<String> clicked_tags){
        super(titol, descripcio, dataini, datafi, hora, direccio,"1",  'A', lat, lng, lang, clicked_tags);
        //TODO: Quan es crea un post, esta hardcodejat que l'owner es l'1
    }

    public Post_Activitat(Post post) {
        super(post);
        this.n_max = 20;
        this.n_act = 10;
    }

    public int getN_max() {
        return n_max;
    }

    public void setN_max(int n_max) {
        this.n_max = n_max;
    }

    public int getN_act() {
        return n_act;
    }

    public void setN_act(int n_act) {
        this.n_act = n_act;
    }
}
