package com.test.business.results.api;

import com.google.gson.Gson;
import com.test.business.Serializer;
import com.test.business.results.ResultsControllerImpl;
import com.test.business.results.model.ResultsSerializerImpl;
import com.test.data.api.NetworkController;
import com.test.data.api.NetworkControllerFactory;

public class ResultsControllerFactory {

    private ResultsControllerFactory() {
    }

    public static ResultsController getResultsController() {
        NetworkController networkController = NetworkControllerFactory.getNetworkController("https://storage.googleapis.com/");
        String resultsUrl = "cdn-og-test-api/test-task/results.json";
        Gson gson = new Gson();
        Serializer serializer = new ResultsSerializerImpl(gson);
        return new ResultsControllerImpl(networkController, resultsUrl, serializer);
    }
}
