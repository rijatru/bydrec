package com.test.business.results.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AggregateScore {

    @SerializedName("home")
    @Expose
    private int home;
    @SerializedName("away")
    @Expose
    private int away;
    @SerializedName("winner")
    @Expose
    private String winner;

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getAway() {
        return away;
    }

    public void setAway(int away) {
        this.away = away;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
