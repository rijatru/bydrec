package com.test.data;

import androidx.annotation.NonNull;

import com.test.data.api.DataRequest;
import com.test.data.api.DataResponse;
import com.test.data.api.NetworkController;

import org.junit.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NetworkControllerIntegrationTest {

    @NonNull
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @NonNull
    private OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @NonNull
    private OkHttpClient getInterceptedOkHttpClient(HttpLoggingInterceptor interceptor, int code, String message) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new NetworkInterceptor(code, message))
                .build();
    }

    @NonNull
    private Retrofit getRetrofit(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build();
    }

    @Test
    public void test_get_withValidUrl_returnsObject() {
        String baseUrl = "https://storage.googleapis.com/";
        HttpClientImpl httpClient = new HttpClientImpl(getRetrofit(baseUrl, getOkHttpClient(getHttpLoggingInterceptor())));
        ServerService serverService = httpClient.getServerServiceInstance(ServerService.class);
        DataRequest dataRequest = mock(DataRequest.class);
        when(dataRequest.getUrl()).thenReturn("cdn-og-test-api/test-task/fixtures.json");
        NetworkController networkController = new NetworkControllerImpl(serverService);

        DataResponse response = networkController.get(dataRequest);

        assertFalse(response.isEmpty());
    }

    @Test
    public void test_get_with400Error_returnsEmpty() {
        String baseUrl = "https://storage.googleapis.com/";
        HttpClientImpl httpClient = new HttpClientImpl(getRetrofit(baseUrl, getInterceptedOkHttpClient(getHttpLoggingInterceptor(), 400, "client error")));
        ServerService serverService = httpClient.getServerServiceInstance(ServerService.class);
        DataRequest dataRequest = mock(DataRequest.class);
        when(dataRequest.getUrl()).thenReturn("cdn-og-test-api/test-task/fixtures.json");
        NetworkController networkController = new NetworkControllerImpl(serverService);

        DataResponse response = networkController.get(dataRequest);

        assertTrue(response.isEmpty());
    }

    @Test
    public void test_get_with500Error_returnsEmpty() {
        String baseUrl = "https://storage.googleapis.com/";
        HttpClientImpl httpClient = new HttpClientImpl(getRetrofit(baseUrl, getInterceptedOkHttpClient(getHttpLoggingInterceptor(), 500, "server error")));
        ServerService serverService = httpClient.getServerServiceInstance(ServerService.class);
        DataRequest dataRequest = mock(DataRequest.class);
        when(dataRequest.getUrl()).thenReturn("cdn-og-test-api/test-task/fixtures.json");
        NetworkController networkController = new NetworkControllerImpl(serverService);

        DataResponse response = networkController.get(dataRequest);

        assertTrue(response.isEmpty());
    }

    @Test
    public void test_get_withMalformedJson_returnsObject() {
        String expectedResult = "{malformedJson";
        String baseUrl = "https://storage.googleapis.com/";
        HttpClientImpl httpClient = new HttpClientImpl(getRetrofit(baseUrl, getInterceptedOkHttpClient(getHttpLoggingInterceptor(), 200, expectedResult)));
        ServerService serverService = httpClient.getServerServiceInstance(ServerService.class);
        DataRequest dataRequest = mock(DataRequest.class);
        when(dataRequest.getUrl()).thenReturn("cdn-og-test-api/test-task/fixtures.json");
        NetworkController networkController = new NetworkControllerImpl(serverService);

        DataResponse response = networkController.get(dataRequest);

        assertTrue(response.isEmpty());
    }

    @Test
    public void test_get_withInteger_returnsObject() {
        String expectedResult = "1";
        String baseUrl = "https://storage.googleapis.com/";
        HttpClientImpl httpClient = new HttpClientImpl(getRetrofit(baseUrl, getInterceptedOkHttpClient(getHttpLoggingInterceptor(), 200, expectedResult)));
        ServerService serverService = httpClient.getServerServiceInstance(ServerService.class);
        DataRequest dataRequest = mock(DataRequest.class);
        when(dataRequest.getUrl()).thenReturn("cdn-og-test-api/test-task/fixtures.json");
        NetworkController networkController = new NetworkControllerImpl(serverService);

        DataResponse response = networkController.get(dataRequest);

        assertFalse(response.isEmpty());
        assertTrue(response.getData() instanceof Double);
    }

    @Test
    public void test_get_callThrowsException() throws IOException {
        HttpClientImpl httpClient = mock(HttpClientImpl.class);
        ServerService serverService = mock(ServerService.class);
        when(httpClient.getServerServiceInstance(ServerService.class)).thenReturn(serverService);
        Call call = mock(Call.class);
        DataRequest dataRequest = mock(DataRequest.class);
        when(call.execute()).thenThrow(IOException.class);
        when(serverService.get(dataRequest.getUrl())).thenReturn(call);
        NetworkController networkController = new NetworkControllerImpl(serverService);

        DataResponse response = networkController.get(dataRequest);

        assertTrue(response.isEmpty());
    }
}
