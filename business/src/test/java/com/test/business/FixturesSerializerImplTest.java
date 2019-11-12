package com.test.business;

import com.google.gson.Gson;
import com.test.business.fixtures.model.FixturesSerializerImpl;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class FixturesSerializerImplTest {

    @Test
    public void test_deserializeData_returnsListOfObjects() {
        String fixturesString = "[{\"id\":987847,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":43,\"name\":\"Manchester City\",\"shortName\":\"Man City\",\"abbr\":\"MNC\",\"alias\":\"t43\"},\"awayTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"date\":\"2019-02-10T16:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":2691,\"name\":\"Etihad Stadium\"},\"state\":\"preMatch\"},{\"id\":1036495,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":993,\"name\":\"Malmö FF\",\"shortName\":\"Malmö\",\"abbr\":\"MAL\",\"alias\":\"t993\"},\"awayTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"date\":\"2019-02-14T20:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":6,\"name\":\"UEFA Europa League\"},\"stage\":\"Round of 32\",\"leg\":\"1st Leg\"},\"venue\":{\"id\":8829,\"name\":\"Swedbank Stadion\"},\"state\":\"preMatch\"},{\"id\":1046147,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"awayTeam\":{\"id\":1,\"name\":\"Manchester United\",\"shortName\":\"Man Utd\",\"abbr\":\"MNU\",\"alias\":\"t1\"},\"date\":\"2019-02-18T19:30:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":1,\"name\":\"FA Cup\"},\"stage\":\"Round 5\"},\"venue\":{\"id\":35,\"name\":\"Stamford Bridge\"},\"state\":\"preMatch\"},{\"id\":1036511,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"awayTeam\":{\"id\":993,\"name\":\"Malmö FF\",\"shortName\":\"Malmö\",\"abbr\":\"MAL\",\"alias\":\"t993\"},\"date\":\"2019-02-21T20:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":6,\"name\":\"UEFA Europa League\"},\"stage\":\"Round of 32\",\"leg\":\"2nd Leg\"},\"venue\":{\"id\":35,\"name\":\"Stamford Bridge\"},\"state\":\"preMatch\"},{\"id\":987856,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"awayTeam\":{\"id\":36,\"name\":\"Brighton\",\"shortName\":\"Brighton\",\"abbr\":\"BHA\",\"alias\":\"t36\"},\"date\":\"2019-02-24T12:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":35,\"name\":\"Stamford Bridge\"},\"state\":\"postponed\"},{\"id\":1045272,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"awayTeam\":{\"id\":43,\"name\":\"Manchester City\",\"shortName\":\"Man City\",\"abbr\":\"MNC\",\"alias\":\"t43\"},\"date\":\"2019-02-24T16:30:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":2,\"name\":\"Carabao Cup\"},\"stage\":\"Final\"},\"venue\":{\"id\":5,\"name\":\"Wembley Stadium\"},\"state\":\"preMatch\"},{\"id\":987867,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"awayTeam\":{\"id\":6,\"name\":\"Tottenham Hotspur\",\"shortName\":\"Tottenham\",\"abbr\":\"TOT\",\"alias\":\"t6\"},\"date\":\"2019-02-27T20:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":35,\"name\":\"Stamford Bridge\"},\"state\":\"preMatch\"},{\"id\":987876,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":54,\"name\":\"Fulham\",\"shortName\":\"Fulham\",\"abbr\":\"FUL\",\"alias\":\"t54\"},\"awayTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"date\":\"2019-03-03T14:05:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":70,\"name\":\"Craven Cottage\"},\"state\":\"preMatch\"},{\"id\":987884,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"awayTeam\":{\"id\":39,\"name\":\"Wolverhampton Wanderers\",\"shortName\":\"Wolves\",\"abbr\":\"WOL\",\"alias\":\"t39\"},\"date\":\"2019-03-10T14:05:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":35,\"name\":\"Stamford Bridge\"},\"state\":\"preMatch\"},{\"id\":987895,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":11,\"name\":\"Everton\",\"shortName\":\"Everton\",\"abbr\":\"EVT\",\"alias\":\"t11\"},\"awayTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"date\":\"2019-03-17T16:30:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":37,\"name\":\"Goodison Park\"},\"state\":\"preMatch\"},{\"id\":987905,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":97,\"name\":\"Cardiff City\",\"shortName\":\"Cardiff\",\"abbr\":\"CAR\",\"alias\":\"t97\"},\"awayTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"date\":\"2019-03-31T13:05:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":5040,\"name\":\"Cardiff City Stadium\"},\"state\":\"preMatch\"},{\"id\":987913,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"awayTeam\":{\"id\":21,\"name\":\"West Ham United\",\"shortName\":\"West Ham\",\"abbr\":\"WHU\",\"alias\":\"t21\"},\"date\":\"2019-04-06T14:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":35,\"name\":\"Stamford Bridge\"},\"state\":\"preMatch\"},{\"id\":987927,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":14,\"name\":\"Liverpool\",\"shortName\":\"Liverpool\",\"abbr\":\"LIV\",\"alias\":\"t14\"},\"awayTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"date\":\"2019-04-13T14:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":39,\"name\":\"Anfield\"}},{\"id\":987935,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"awayTeam\":{\"id\":90,\"name\":\"Burnley\",\"shortName\":\"Burnley\",\"abbr\":\"BNY\",\"alias\":\"t90\"},\"date\":\"2019-04-20T14:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":35,\"name\":\"Stamford Bridge\"},\"state\":\"preMatch\"},{\"id\":987948,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":1,\"name\":\"Manchester United\",\"shortName\":\"Man Utd\",\"abbr\":\"MNU\",\"alias\":\"t1\"},\"awayTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"date\":\"2019-04-27T14:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":28,\"name\":\"Old Trafford\"},\"state\":\"preMatch\"},{\"id\":987955,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"awayTeam\":{\"id\":57,\"name\":\"Watford\",\"shortName\":\"Watford\",\"abbr\":\"WAT\",\"alias\":\"t57\"},\"date\":\"2019-05-04T14:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":35,\"name\":\"Stamford Bridge\"},\"state\":\"preMatch\"},{\"id\":987966,\"type\":\"FixtureUpcoming\",\"homeTeam\":{\"id\":13,\"name\":\"Leicester City\",\"shortName\":\"Leicester\",\"abbr\":\"LCR\",\"alias\":\"t13\"},\"awayTeam\":{\"id\":8,\"name\":\"Chelsea\",\"shortName\":\"Chelsea\",\"abbr\":\"CHL\",\"alias\":\"t8\"},\"date\":\"2019-05-12T14:00:00.000Z\",\"competitionStage\":{\"competition\":{\"id\":8,\"name\":\"Premier League\"}},\"venue\":{\"id\":2516,\"name\":\"King Power Stadium\"},\"state\":\"preMatch\"}]";
        Gson gson = new Gson();
        Serializer serializer = new FixturesSerializerImpl(gson);

        List<Object> deserializedData = (List<Object>) serializer.deserializeData(new Gson().fromJson(fixturesString, Object.class));

        assertNotNull(deserializedData);
        assertEquals(17, deserializedData.size());
    }

    @Test
    public void test_deserializeData_withNumericString_returnsEmptyList() {
        String numericString = "1";
        Gson gson = new Gson();
        Serializer serializer = new FixturesSerializerImpl(gson);

        List<Object> deserializedData = (List<Object>) serializer.deserializeData(new Gson().fromJson(numericString, Object.class));

        Assert.assertTrue(deserializedData.isEmpty());
    }

    @Test
    public void test_deserializeData_withNonJsonString_returnsEmptyList() {
        String nonJsonString = "nonJsonString";
        Gson gson = new Gson();
        Serializer serializer = new FixturesSerializerImpl(gson);

        List<Object> deserializedData = (List<Object>) serializer.deserializeData(new Gson().fromJson(nonJsonString, Object.class));

        Assert.assertTrue(deserializedData.isEmpty());
    }
}
