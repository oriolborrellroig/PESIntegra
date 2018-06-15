package integra.pesintegra.Logic.Clases;


import java.util.ArrayList;
import java.util.List;

public class Post_Activitat extends Post{

    private int assistentsMax;
    private int n_assistents;

    public Post_Activitat(){
        super('A');
    }

    public Post_Activitat(String titol, String descripcio, String dataini, String datafi, String hora, String direccio, String owner, double lat, double lng, String lang, ArrayList<String> clicked_tags, int nmax){
        super(titol, descripcio, dataini, datafi, hora, direccio, owner, 'A', lat, lng, lang, clicked_tags);
        this.assistentsMax = nmax;
    }

    public int getAssistentsMax() {
        return assistentsMax;
    }

    public void setAssistentsMax(int n_max) {
        this.assistentsMax = n_max;
    }

    public int getN_assistents() {
        return n_assistents;
    }

    public void setN_assistents(int n_act) {
        this.n_assistents = n_act;
    }
}
