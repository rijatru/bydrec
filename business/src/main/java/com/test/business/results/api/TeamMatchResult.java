package com.test.business.results.api;

import com.test.business.results.model.AggregateScore;
import com.test.business.results.model.AwayTeam;
import com.test.business.results.model.CompetitionStage;
import com.test.business.results.model.HomeTeam;
import com.test.business.results.model.Venue;

public interface TeamMatchResult {

    Integer getId();

    HomeTeam getHomeTeam();

    AwayTeam getAwayTeam();

    String getDate();

    Venue getVenue();

    String getState();

    CompetitionStage getCompetitionStage();

    AggregateScore getAggregateScore();

    String getType();
}
