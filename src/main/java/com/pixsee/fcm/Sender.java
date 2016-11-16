package com.pixsee.fcm;

import com.google.gson.JsonObject;
import com.sun.deploy.net.HttpRequest;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Tudor on 15-Nov-16.
 */
public class Sender {
    private final String serverKey;
    private Interceptor interceptor;
    private OkHttpClient httpClient;
    private Retrofit retrofit;

    public Sender(String serverKey) {
        this.serverKey = serverKey;
        interceptor = createInterceptor();
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        retrofit = createRetrofit().build();
    }

    private Retrofit.Builder createRetrofit() {
        return createRetrofit(GsonConverterFactory.create());
    }

    private Retrofit.Builder createRetrofit(Factory factory) {
        return new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/fcm/")
                .client(httpClient)
                .addConverterFactory(factory);
    }

    private Interceptor createInterceptor() {
        return chain -> {
            Request request = chain.request().newBuilder()
                    .header("Authorization", String.format("key=%s", Sender.this.serverKey))
                    .header(HttpRequest.CONTENT_TYPE, "application/json")
                    .build();
            return chain.proceed(request);
        };
    }

    public void send(String to) {
        retrofit.create(GoogleCSS.class)
                .send(new Message(to))
                .enqueue(new retrofit2.Callback<JsonObject>() {
                    public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                        System.out.println(response.body());

                    }

                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        t.printStackTrace();

                    }
                });
    }

    private interface GoogleCSS {
        @POST("send")
        Call<JsonObject> send(@Body Message to);
    }

    class Message {
        String to;

        public Message(String to) {
            this.to = to;
        }
    }
}
