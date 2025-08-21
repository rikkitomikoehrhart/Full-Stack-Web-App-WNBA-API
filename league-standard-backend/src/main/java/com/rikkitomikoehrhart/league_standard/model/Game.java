package com.rikkitomikoehrhart.league_standard.model;

import java.time.LocalDate;

public class Game {
    private String id;
    private Team homeTeam;
    private Team awayTeam;
    private LocalDate scheduled;
    private String status;
    private int homeScore;
    private int awayScore;
    private int seasonYear;

    public Game() {};

    public Game(String id, Team homeTeam, Team awayTeam, LocalDate scheduled, String status, int homeScore, int awayScore, int seasonYear) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scheduled = scheduled;
        this.status = status;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.seasonYear = seasonYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }


    public String getHomeTeamID() {
        if (this.homeTeam == null) {
            return null;
        }
        return homeTeam.getId();
    }

    public String getAwayTeamID() {
        if (this.awayTeam == null) {
            return null;
        }
        return awayTeam.getId();
    }
}
