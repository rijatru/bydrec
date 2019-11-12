package com.test.business.fixtures;

import com.test.business.Serializer;
import com.test.business.fixtures.api.Fixture;
import com.test.business.fixtures.api.FixturesController;
import com.test.data.api.DataRequest;
import com.test.data.api.DataResponse;
import com.test.data.api.NetworkController;

import java.util.List;

public class FixturesControllerImpl implements FixturesController {

    private final NetworkController networkController;
    private final String fixturesUrl;
    private final Serializer serializer;

    public FixturesControllerImpl(NetworkController networkController, String fixturesUrl, Serializer serializer) {
        this.networkController = networkController;
        this.fixturesUrl = fixturesUrl;
        this.serializer = serializer;
    }

    @Override
    public List<Fixture> getFixtures() {
        DataRequest dataRequest = new DataRequest(fixturesUrl);
        DataResponse response = networkController.get(dataRequest);
        return (List<Fixture>) serializer.deserializeData(response.getData());
    }
}
