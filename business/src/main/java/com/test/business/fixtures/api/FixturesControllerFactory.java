package com.test.business.fixtures.api;

import com.google.gson.Gson;
import com.test.business.fixtures.model.FixturesSerializerImpl;
import com.test.business.Serializer;
import com.test.business.fixtures.FixturesControllerImpl;
import com.test.data.api.NetworkController;
import com.test.data.api.NetworkControllerFactory;

public class FixturesControllerFactory {

    private FixturesControllerFactory() {
    }

    public static FixturesController getFixturesController() {
        NetworkController networkController = NetworkControllerFactory.getNetworkController("https://storage.googleapis.com/");
        String fixturesUrl = "cdn-og-test-api/test-task/fixtures.json";
        Gson gson = new Gson();
        Serializer serializer = new FixturesSerializerImpl(gson);
        return new FixturesControllerImpl(networkController, fixturesUrl, serializer);
    }
}
