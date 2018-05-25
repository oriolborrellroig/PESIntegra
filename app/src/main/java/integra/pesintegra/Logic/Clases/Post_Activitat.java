package integra.pesintegra.Logic.Clases;


import java.util.ArrayList;
import java.util.List;

public class Post_Activitat extends Post{

    //n_max = nombre maxim de persones en aquella activitat
    private int assistentsMax;
    //n_act = nombre de persones apuntades actualment a aquella activitat
    private int n_assistens;

    public Post_Activitat(){
        super('A');
    }

    public Post_Activitat(String titol, String descripcio, String dataini, String datafi, String hora, String direccio, String owner, double lat, double lng, String lang, ArrayList<String> clicked_tags, int nmax){
        super(titol, descripcio, dataini, datafi, hora, direccio, owner, 'A', lat, lng, lang, clicked_tags);
        this.assistentsMax = nmax;
    }

    public Post_Activitat(Post post) {
        super(post);
        this.assistentsMax = 20;
        this.n_assistens = 10;
    }

    public int getAssistentsMax() {
        return assistentsMax;
    }

    public void setAssistentsMax(int n_max) {
        this.assistentsMax = n_max;
    }

    public int getN_assistens() {
        return n_assistens;
    }

    public void setN_assistens(int n_act) {
        this.n_assistens = n_act;
    }
}
