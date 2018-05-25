package integra.pesintegra.Controllers;


import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.Post_Habitatge;
import integra.pesintegra.Logic.Clases.Sessio;
import integra.pesintegra.Services.ImageService;
import integra.pesintegra.Services.ServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorDomini extends  AbstractBaseController{

    private static ServiceManager serviceManager;
    private static Sessio sessio;
    public  ImageBM result;

    public ControladorDomini () {}

    void setSessio(Sessio usuari) {

        sessio = usuari;
        serviceManager = new ServiceManager(usuari.getToken(), usuari.getUsername());
    }

    public void creaPostActivitat(Post_Activitat activitat) {

    }

    public void creaPostHabitatge(Post_Habitatge habitatge) {
    }

    public void creaPostFeina(Post_Feina feina) {
    }


    // Idea és passar tots els post de la database per comprobar quins s'haurien de guardar com expired. (es podria passar com a llista)
    public Boolean post_Caducat(Post post) {
        return comprovaDataExpired(post.getDataFi());
    }

    public void logout() {
        sessio.resetSessio();
    }


    ServiceManager getServiceManager() {
        return serviceManager;
    }

    String getSessioUser() {
        return sessio.getUsername();
    }


    String getSessioToken() {
        return sessio.getToken();

    }

    public ImageBM getImage(String id, String owner){

        ImageService service = ServiceManager.getImageService();
        Call<ImageBM> ccall = service.getImage(id, owner);
        ccall.enqueue(new Callback<ImageBM>() {
            @Override
            public void onResponse(Call<ImageBM> call, Response<ImageBM> response) {
                result = response.body();
            }

            @Override
            public void onFailure(Call<ImageBM> call, Throwable t) {
                Log.d("sadasds","aaa");
            }
        });
        return result;
    }

    public void storeImage(ImageBM i){

        ImageService service = ServiceManager.getImageService();
        Call<Void> ccall = service.createImage(i);
        ccall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("ole","aaa");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("sadasds","aaa");
            }
        });
    }
}
