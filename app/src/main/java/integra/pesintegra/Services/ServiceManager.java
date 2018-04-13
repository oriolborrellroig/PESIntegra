package integra.pesintegra.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import integra.pesintegra.Logic.Adapter.JsonPostAdapter;
import integra.pesintegra.Logic.Clases.Post;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceManager {

    public static PostService getPostService() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Post.class, new JsonPostAdapter());
        Gson gsonExt = builder.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pesintegratest.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(gsonExt))
                .build();

        final PostService service = retrofit.create(PostService.class);
        return service;
    }


}