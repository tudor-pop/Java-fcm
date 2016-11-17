package org.pixsee.fcm;

import com.google.gson.JsonObject;
import com.sun.deploy.net.HttpRequest;
import com.sun.istack.internal.NotNull;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter.Factory;
import retrofit2.Response;
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
    private HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    public Sender(String serverKey) {
        this.serverKey = serverKey;
        interceptor = createInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
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

    public void setLoggingLevel(@NotNull HttpLoggingInterceptor.Level level) {
        httpLoggingInterceptor.setLevel(level);
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

    public void send(Message message) {
        send(message, new EmptyCallback());
    }

    public void send(Message message, Callback callback) {
        retrofit.create(GoogleCSS.class)
                .send(message)
                .enqueue(callback);
    }

    private interface GoogleCSS {
        @POST("send")
        Call<JsonObject> send(@Body Message to);
    }

    private class EmptyCallback implements Callback {
        @Override
        public void onResponse(Call call, Response response) {
            System.out.println(response.body());
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            t.printStackTrace();
        }
    }
}
