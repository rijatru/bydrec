package com.test.data;

public interface HttpClient {

    <T> T getServerServiceInstance(Class<T> service);
}
