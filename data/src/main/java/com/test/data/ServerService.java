package com.test.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ServerService {

    @GET
    Call<Object> get(@Url String url);
}
