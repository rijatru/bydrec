package com.test.business.results;

import com.test.business.Serializer;
import com.test.business.results.api.TeamMatchResult;
import com.test.business.results.api.ResultsController;
import com.test.data.api.DataRequest;
import com.test.data.api.DataResponse;
import com.test.data.api.NetworkController;

import java.util.List;

public class ResultsControllerImpl implements ResultsController {

    private final NetworkController networkController;
    private final String fixturesUrl;
    private final Serializer serializer;

    public ResultsControllerImpl(NetworkController networkController, String fixturesUrl, Serializer serializer) {
        this.networkController = networkController;
        this.fixturesUrl = fixturesUrl;
        this.serializer = serializer;
    }

    @Override
    public List<TeamMatchResult> getResults() {
        DataRequest dataRequest = new DataRequest(fixturesUrl);
        DataResponse response = networkController.get(dataRequest);
        return (List<TeamMatchResult>) serializer.deserializeData(response.getData());
    }
}
