package com.rikkitomikoehrhart.league_standard.model;

public class Team {
    private String id;
    private String alias;
    private String market;
    private String name;
    private int yearFounded;
    private String mascot;
    private String owner;

    public Team() {};

    public Team(String id, String alias, String market, String name, int yearFounded, String mascot, String owner) {
        this.id = id;
        this.alias = alias;
        this.market = market;
        this.name = name;
        this.yearFounded = yearFounded;
        this.mascot = mascot;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
