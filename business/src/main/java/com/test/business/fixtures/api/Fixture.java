package com.test.business.fixtures.api;

import com.test.business.fixtures.model.AwayTeam;
import com.test.business.fixtures.model.CompetitionStage;
import com.test.business.fixtures.model.HomeTeam;
import com.test.business.fixtures.model.Venue;

public interface Fixture {

    Integer getId();

    HomeTeam getHomeTeam();

    AwayTeam getAwayTeam();

    String getDate();

    CompetitionStage getCompetitionStage();

    Venue getVenue();

    String getState();

    String getType();
}
