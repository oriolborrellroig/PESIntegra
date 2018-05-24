package integra.pesintegra.Controllers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDominiEditActivity extends ControladorDomini {

    private ControladorPresentacioEditActivity Cpresentacio;


    ControladorDominiEditActivity(ControladorPresentacioEditActivity allposts) {
        this.Cpresentacio = allposts;
    }


    public void editPost(String originPostId, Post post, Uri uri) {
        post.setOwner(this.getSessioUser());
        PostService service = ServiceManager.getPostService();
        Call<Void> createCall = service.updatePost(originPostId,post);
        Log.d("Location", "Inside controlador Domini Edit Post!!");
        createCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Cpresentacio.showNewPost();
                //Log.d("Location", "DominiEditPost response received!");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });



    }

    public LatLng getLoc(String lloc, Context context) {
        Geocoder gc = new Geocoder(context);
        try {
            List<Address> list = gc.getFromLocationName(lloc, 1);
            int i = 0;
            while (list.size() == 0 && i < 10){
                ++i;
                list = gc.getFromLocationName(lloc, 1);
            }
            if(list.size() > 0){
                Address address = list.get(0);
                double lat = address.getLatitude();
                double lng = address.getLongitude();
                return new LatLng(lat, lng);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;

    }
}
