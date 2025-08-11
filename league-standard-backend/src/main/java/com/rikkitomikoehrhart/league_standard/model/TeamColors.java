package com.rikkitomikoehrhart.league_standard.model;

public class TeamColors {
    private int id;
    private Team team;
    private String colorType;
    private String hexCode;

    public TeamColors() {};

    public TeamColors(int id, Team team, String colorType, String hexCode) {
        this.id = id;
        this.team = team;
        this.colorType = colorType;
        this.hexCode = hexCode;
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

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }
}
