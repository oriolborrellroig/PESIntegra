package integra.pesintegra.Controllers;

import java.util.ArrayList;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.MapsActivity;


public class ControladorPresentacioMapsActivity extends ControladorPresentacio {

    private static ControladorDominiMapsActivity CDMaps;
    private MapsActivity activity;

    public ControladorPresentacioMapsActivity(MapsActivity maps) {
        CDMaps = new ControladorDominiMapsActivity(this);
        this.activity = maps;
    }

    public void getPosts(String id){
        CDMaps.getPosts(id);
    }

    public void setPosts(ArrayList<Post> posts) {
        activity.setPosts(posts);
    }
}
