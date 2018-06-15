package integra.pesintegra.Presentation;

import android.content.Intent;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.Controllers.ControladorPresentacioMapsActivity;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private ControladorPresentacioMapsActivity cp;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        cp = new ControladorPresentacioMapsActivity(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        Intent intent = getIntent();

        if(intent.getStringExtra("origin").equals("post")) {
            double lat = intent.getDoubleExtra("lat", 0);
            double lng = intent.getDoubleExtra("lng", 0);
            // Add a marker in Sydney and move the camera
            LatLng coord = new LatLng(lat, lng);
            googleMap.addMarker(new MarkerOptions().position(coord).title(intent.getStringExtra("loc")));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coord, 17.0f));
        }

        else{
            String current_user = cp.getSessioUser();
            cp.getPosts(current_user);
        }


    }

    public void setPosts(ArrayList<Post> posts) {
        LatLng coord;
        if(posts == null){
            googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("No posts in calendar"));
        }
        else {
            for (int i = 0; i < posts.size(); ++i) {
                coord = new LatLng(posts.get(i).getLat(), posts.get(i).getLng());
                googleMap.addMarker(new MarkerOptions().position(coord).title(posts.get(i).getLocalitzacio()));

            }
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0,0), 3.0f));
    }
}
