package com.test.data;

import com.test.data.api.DataRequest;
import com.test.data.api.DataResponse;
import com.test.data.api.NetworkController;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;

public final class NetworkControllerImpl implements NetworkController {

    private final ServerService serverService;

    public NetworkControllerImpl(ServerService serverService) {
        this.serverService = serverService;
    }

    @NotNull
    @Override
    public DataResponse get(@NotNull DataRequest request) {
        try {
            return getDataResponse(request);
        } catch (IOException e) {
            return DataResponse.Companion.getEMPTY();
        }
    }

    @NotNull
    private DataResponse getDataResponse(@NotNull DataRequest request) throws IOException {
        Call<Object> call = serverService.get(request.getUrl());
        Object response = call.execute().body();
        if (response != null) {
            return new DataResponse<>(response);
        } else {
            return DataResponse.Companion.getEMPTY();
        }
    }
}
