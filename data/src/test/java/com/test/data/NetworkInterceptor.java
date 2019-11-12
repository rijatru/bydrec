package com.test.data;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkInterceptor implements Interceptor {

    private final int code;
    private final String message;

    NetworkInterceptor(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Response intercept(Chain chain) {
        return new Response.Builder()
                .code(code)
                .message(message)
                .protocol(Protocol.HTTP_1_1)
                .request(chain.request())
                .body(ResponseBody.create(MediaType.parse("application/json"), message.getBytes()))
                .build();
    }
}
