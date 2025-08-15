package com.rikkitomikoehrhart.league_standard.model;

public class TeamStanding {
    private String id;
    private Team team;
    private int wins;
    private int loses;
    private float win_pct;
    private float points_for;
    private float points_against;
    private float point_diff;
    private int league_rank;

    public TeamStanding() {};
    public TeamStanding(String id, Team team, int wins, int loses, float win_pct, float points_for, float points_against, float point_diff, int league_rank) {
        this.id = id;
        this.team = team;
        this.wins = wins;
        this.loses = loses;
        this.win_pct = win_pct;
        this.points_for = points_for;
        this.points_against = points_against;
        this.point_diff = point_diff;
        this.league_rank = league_rank;
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

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public float getWin_pct() {
        return win_pct;
    }

    public void setWin_pct(float win_pct) {
        this.win_pct = win_pct;
    }

    public float getPoints_for() {
        return points_for;
    }

    public void setPoints_for(float points_for) {
        this.points_for = points_for;
    }

    public float getPoints_against() {
        return points_against;
    }

    public void setPoints_against(float points_against) {
        this.points_against = points_against;
    }

    public float getPoint_diff() {
        return point_diff;
    }

    public void setPoint_diff(float point_diff) {
        this.point_diff = point_diff;
    }

    public int getLeague_rank() {
        return league_rank;
    }

    public void setLeague_rank(int league_rank) {
        this.league_rank = league_rank;
    }

    public String getTeamID() {
        return team.getId();
    }

    public void setTeamID(String id) {
        team.setId(id);
    }
}
