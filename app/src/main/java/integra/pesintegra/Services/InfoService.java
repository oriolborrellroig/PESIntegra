package integra.pesintegra.Services;

import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.GET;

public interface InfoService {

    @GET("info/get")
    Call<JsonObject> getInfo();
}
