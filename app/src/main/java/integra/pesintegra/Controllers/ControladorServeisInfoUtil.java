package integra.pesintegra.Controllers;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import integra.pesintegra.Presentation.InformationActivity;
import integra.pesintegra.Services.InfoService;
import integra.pesintegra.Services.ServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ControladorServeisInfoUtil extends ControladorServeis{

    private InformationActivity activity;
    private Context context;

    public ControladorServeisInfoUtil (InformationActivity callActivity, Context cont) {
        this.activity = callActivity;
        this.context = cont;
    }


    public void getInfo () {
        InfoService service = this.getServiceManager().getInfoService();
        Call<JsonObject> createCall = service.getInfo();
        createCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                activity.posaInformacio(response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


    }
}
