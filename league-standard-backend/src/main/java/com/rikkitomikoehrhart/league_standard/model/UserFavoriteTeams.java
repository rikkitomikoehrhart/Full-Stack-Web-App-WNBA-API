package com.rikkitomikoehrhart.league_standard.model;

public class UserFavoriteTeams {
    private String id;
    private Team team;

    public UserFavoriteTeams() {};

    public UserFavoriteTeams(String id, Team team) {
        this.id = id;
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getTeamID() {
        return team.getId();
    }
}
