package integra.pesintegra.Services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import integra.pesintegra.Logic.Adapter.JsonPostAdapter;
import integra.pesintegra.Logic.Clases.Post;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceManager {

    private static String user;
    private static String token;

    public ServiceManager(){}

    public ServiceManager (String token, String user) {
        this.token = token;
        this.user = user;
    }

    public static PostService getPostService() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
                                      @Override
                                      public okhttp3.Response intercept(Chain chain) throws IOException {
                                          Request original = chain.request();

                                          Request request = original.newBuilder()
                                                  .header("Authorization",  token)
                                                  .header("user", user)
                                                  .method(original.method(), original.body())
                                                  .build();

                                          return chain.proceed(request);
                                      }
                                  });
        OkHttpClient client = httpClient.build();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Post.class, new JsonPostAdapter());
        Gson gsonExt = builder.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pesintegratest.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(gsonExt))
                .client(client)
                .build();

        final PostService service = retrofit.create(PostService.class);
        return service;
    }

    public static ImageService getImageService() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization",  token)
                        .header("user", user)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        GsonBuilder builder = new GsonBuilder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pesintegratest.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final ImageService service = retrofit.create(ImageService.class);
        return service;
    }

    public static InfoService getInfoService() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization",  token)
                        .header("user", user)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        GsonBuilder builder = new GsonBuilder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pesintegratest.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final InfoService service = retrofit.create(InfoService.class);
        return service;
    }

    public static UserService getUserService() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization",  token)
                        .header("user", user)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();

        GsonBuilder builder = new GsonBuilder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pesintegratest.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final UserService service = retrofit.create(UserService.class);
        return service;
    }

    public static UserService getLoginService() {


        GsonBuilder builder = new GsonBuilder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pesintegratest.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UserService service = retrofit.create(UserService.class);
        return service;
    }

}
