package com.test.data.api;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.data.HttpClient;
import com.test.data.HttpClientImpl;
import com.test.data.NetworkControllerImpl;
import com.test.data.ServerService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkControllerFactory {

    private NetworkControllerFactory() {
    }

    public static NetworkController getNetworkController(String baseUrl) {
        HttpLoggingInterceptor httpLoggingInterceptor = getHttpLoggingInterceptor();
        OkHttpClient okHttpClient = getOkHttpClient(httpLoggingInterceptor);
        Retrofit retrofit = getRetrofit(baseUrl, okHttpClient);
        HttpClient httpClient = new HttpClientImpl(retrofit);
        ServerService serverService = httpClient.getServerServiceInstance(ServerService.class);
        return new NetworkControllerImpl(serverService);
    }

    @NonNull
    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @NonNull
    private static OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @NonNull
    private static Retrofit getRetrofit(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build();
    }
}
