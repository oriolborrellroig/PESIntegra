package integra.pesintegra.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceManager {

    public static PostService getPostService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pesintegratest.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final PostService service = retrofit.create(PostService.class);
        return service;
    }
}
