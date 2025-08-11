package com.rikkitomikoehrhart.league_standard.model;

public class UserFavoriteTeams {
    private int id;
    private Team team;

    public UserFavoriteTeams() {};

    public UserFavoriteTeams(int id, Team team) {
        this.id = id;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
