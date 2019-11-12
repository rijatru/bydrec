package com.test.business.results.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.business.results.api.TeamMatchResult;

public class TeamMatchResultImpl implements TeamMatchResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("homeTeam")
    @Expose
    private HomeTeam homeTeam;
    @SerializedName("awayTeam")
    @Expose
    private AwayTeam awayTeam;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("competitionStage")
    @Expose
    private CompetitionStage competitionStage;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("score")
    @Expose
    private Score score;
    @SerializedName("penaltyScore")
    @Expose
    private PenaltyScore penaltyScore;
    @SerializedName("aggregateScore")
    @Expose
    private AggregateScore aggregateScore;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    @Override
    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public CompetitionStage getCompetitionStage() {
        return competitionStage;
    }

    public void setCompetitionStage(CompetitionStage competitionStage) {
        this.competitionStage = competitionStage;
    }

    @Override
    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public PenaltyScore getPenaltyScore() {
        return penaltyScore;
    }

    public void setPenaltyScore(PenaltyScore penaltyScore) {
        this.penaltyScore = penaltyScore;
    }

    @Override
    public AggregateScore getAggregateScore() {
        if (aggregateScore == null && score !=null) {
            AggregateScore tempScore = new AggregateScore();
            tempScore.setAway(score.getAway());
            tempScore.setHome(score.getHome());
            aggregateScore = tempScore;
        }
        return aggregateScore;
    }

    public void setAggregateScore(AggregateScore aggregateScore) {
        this.aggregateScore = aggregateScore;
    }
}
