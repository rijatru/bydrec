package com.test.business.fixtures.model;

import com.google.gson.Gson;
import com.test.business.Serializer;
import com.test.business.fixtures.api.Fixture;
import com.test.business.fixtures.model.FixtureImpl;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FixturesSerializerImpl implements Serializer {

    private final Gson gson;

    public FixturesSerializerImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> List<Fixture> deserializeData(T data) {
        try {
            return getFixtures(data);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @NotNull
    private <T> List<Fixture> getFixtures(T data) {
        String fixturesJson = gson.toJson(data);
        Fixture[] fixturesArray = gson.fromJson(fixturesJson, FixtureImpl[].class);
        return Arrays.asList(fixturesArray);
    }
}
