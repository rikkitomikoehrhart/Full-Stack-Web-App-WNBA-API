package com.rikkitomikoehrhart.league_standard.model;

import java.time.LocalDate;

public class Game {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private LocalDate scheduled;
    private int homeScore;
    private int awayScore;

    public Game() {};

    public Game(int id, Team homeTeam, Team awayTeam, LocalDate scheduled, int homeScore, int awayScore) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scheduled = scheduled;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public LocalDate getScheduled() {
        return scheduled;
    }

    public void setScheduled(LocalDate scheduled) {
        this.scheduled = scheduled;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }
}
