package com.test.business.results.model;

import com.google.gson.Gson;
import com.test.business.Serializer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultsSerializerImpl implements Serializer {

    private final Gson gson;

    public ResultsSerializerImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> List<TeamMatchResultImpl> deserializeData(T data) {
        try {
            return getObjects(data);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @NotNull
    private <T> List<TeamMatchResultImpl> getObjects(T data) {
        String objectsJson = gson.toJson(data);
        TeamMatchResultImpl[] resultsArray = gson.fromJson(objectsJson, TeamMatchResultImpl[].class);
        return Arrays.asList(resultsArray);
    }
}
