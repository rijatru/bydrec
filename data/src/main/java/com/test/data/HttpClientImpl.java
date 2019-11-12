package com.test.data;

import retrofit2.Retrofit;

public class HttpClientImpl implements HttpClient {

    private final Retrofit retrofit;

    public HttpClientImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public <T> T getServerServiceInstance(Class<T> service) {
        return retrofit.create(service);
    }
}
