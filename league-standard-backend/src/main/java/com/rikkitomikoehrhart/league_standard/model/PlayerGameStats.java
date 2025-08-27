package com.rikkitomikoehrhart.league_standard.model;

public class PlayerGameStats {
    private String playerID;
    private String minutesPlayed;
    private int fieldGoalsMade;
    private float fieldGoalPercentage;
    private int threePointsMade;
    private float threePointPercentage;
    private int twoPointsMade;
    private float twoPointPercentage;
    private int freeThrowsMade;
    private float freeThrowPercentage;
    private int offensiveRebounds;
    private int defensiveRebounds;
    private int assists;
    private int turnovers;
    private int steals;
    private int blocks;
    private int personalFouls;
    private int techFouls;
    private int flagrant_fouls;
    private int points;
    private int foulsDrawn;


    public PlayerGameStats() {}
    public PlayerGameStats(String playerID, String minutesPlayed, int fieldGoalsMade,
                           float fieldGoalPercentage, int threePointsMade, float threePointPercentage,
                           int twoPointsMade, float twoPointPercentage, int freeThrowsMade, float freeThrowPercentage,
                           int offensiveRebounds, int defensiveRebounds, int assists, int turnovers, int steals,
                           int blocks, int personalFouls, int techFouls, int flagrant_fouls, int points,
                           int foulsDrawn) {
        this.playerID = playerID;
        this.minutesPlayed = minutesPlayed;
        this.fieldGoalsMade = fieldGoalsMade;
        this.fieldGoalPercentage = fieldGoalPercentage;
        this.threePointsMade = threePointsMade;
        this.threePointPercentage = threePointPercentage;
        this.twoPointsMade = twoPointsMade;
        this.twoPointPercentage = twoPointPercentage;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrowPercentage = freeThrowPercentage;
        this.offensiveRebounds = offensiveRebounds;
        this.defensiveRebounds = defensiveRebounds;
        this.assists = assists;
        this.turnovers = turnovers;
        this.steals = steals;
        this.blocks = blocks;
        this.personalFouls = personalFouls;
        this.techFouls = techFouls;
        this.flagrant_fouls = flagrant_fouls;
        this.points = points;
        this.foulsDrawn = foulsDrawn;
    }

    public String getPlayerID() { return playerID; }
    public void setPlayerID(String playerID) { this.playerID = playerID; }
    public String getMinutesPlayed() { return minutesPlayed; }
    public void setMinutesPlayed(String minutesPlayed) { this.minutesPlayed = minutesPlayed; }
    public int getFieldGoalsMade() { return fieldGoalsMade; }
    public void setFieldGoalsMade(int fieldGoalsMade) { this.fieldGoalsMade = fieldGoalsMade; }
    public float getFieldGoalPercentage() { return fieldGoalPercentage; }
    public void setFieldGoalPercentage(float fieldGoalPercentage) { this.fieldGoalPercentage = fieldGoalPercentage; }
    public int getThreePointsMade() { return threePointsMade; }
    public void setThreePointsMade(int threePointsMade) { this.threePointsMade = threePointsMade; }
    public float getThreePointPercentage() { return threePointPercentage; }
    public void setThreePointPercentage(float threePointPercentage) { this.threePointPercentage = threePointPercentage; }
    public int getTwoPointsMade() {
        return twoPointsMade;
    }
    public void setTwoPointsMade(int twoPointsMade) {
        this.twoPointsMade = twoPointsMade;
    }
    public float getTwoPointPercentage() {
        return twoPointPercentage;
    }
    public void setTwoPointPercentage(float twoPointPercentage) {
        this.twoPointPercentage = twoPointPercentage;
    }
    public int getFreeThrowsMade() { return freeThrowsMade; }
    public void setFreeThrowsMade(int freeThrowsMade) { this.freeThrowsMade = freeThrowsMade; }
    public float getFreeThrowPercentage() { return freeThrowPercentage; }
    public void setFreeThrowPercentage(float freeThrowPercentage) { this.freeThrowPercentage = freeThrowPercentage; }
    public int getOffensiveRebounds() { return offensiveRebounds; }
    public void setOffensiveRebounds(int offensiveRebounds) { this.offensiveRebounds = offensiveRebounds; }
    public int getDefensiveRebounds() { return defensiveRebounds; }
    public void setDefensiveRebounds(int defensiveRebounds) { this.defensiveRebounds = defensiveRebounds; }
    public int getAssists() { return assists; }
    public void setAssists(int assists) { this.assists = assists; }
    public int getTurnovers() { return turnovers; }
    public void setTurnovers(int turnovers) { this.turnovers = turnovers; }
    public int getSteals() { return steals; }
    public void setSteals(int steals) { this.steals = steals; }
    public int getBlocks() { return blocks; }
    public void setBlocks(int blocks) { this.blocks = blocks; }
    public int getPersonalFouls() { return personalFouls; }
    public void setPersonalFouls(int personalFouls) { this.personalFouls = personalFouls; }
    public int getTechFouls() {
        return techFouls;
    }
    public void setTechFouls(int techFouls) {
        this.techFouls = techFouls;
    }
    public int getFlagrant_fouls() {
        return flagrant_fouls;
    }
    public void setFlagrant_fouls(int flagrant_fouls) {
        this.flagrant_fouls = flagrant_fouls;
    }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    public int getFoulsDrawn() {
        return foulsDrawn;
    }
    public void setFoulsDrawn(int foulsDrawn) {
        this.foulsDrawn = foulsDrawn;
    }

}
