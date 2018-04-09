package integra.pesintegra.Logic.Clases;

public class Post_Activitat extends Post{

    //n_max = nombre maxim de persones en aquella activitat
    private int n_max;

    public Post_Activitat(String titol, String descripcio, String dataini, String datafi, String hora, String direccio){
        super(titol, descripcio, dataini, datafi, hora, direccio, 'A');
    }

    public int getN_max() {
        return n_max;
    }

    public void setN_max(int n_max) {
        this.n_max = n_max;
    }
}
