package integra.pesintegra.Logic.Clases;

public class Post_Habitatge extends Post {

    public Post_Habitatge(){
        super('H');
    }

    public Post_Habitatge(String titol, String descripcio, String dataini, String datafi, String hora, String direccio){
        super(titol, descripcio, dataini, datafi, hora, direccio, 'H');
    }
}
