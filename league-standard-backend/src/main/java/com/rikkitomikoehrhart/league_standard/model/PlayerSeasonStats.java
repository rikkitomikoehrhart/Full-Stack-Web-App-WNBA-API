package com.rikkitomikoehrhart.league_standard.model;

public class PlayerSeasonStats {
    private int id;
    private String playerID;
    private int games_played;
    private int games_started;
    private int minutes;
    private int field_goals_made;
    private float field_goals_pct;
    private int two_points_made;
    private float two_points_pct;
    private int three_points_made;
    private float three_points_pct;
    private int free_throws_made;
    private float free_throws_pct;
    private int offensive_rebounds;
    private int defensive_rebounds;
    private int assists;
    private int turnovers;
    private int steals;
    private int blocks;
    private int personal_fouls;
    private int tech_fouls;
    private int points;
    private float avg_points_per_game;
    private int flagrant_fouls;
    private int fouls_drawn;

    public PlayerSeasonStats() {}
    public PlayerSeasonStats(int id, String playerID, int games_played, int games_started, int minutes,
                             int field_goals_made, float field_goals_pct, int two_points_made, float two_points_pct,
                             int three_points_made, float three_points_pct, int free_throws_made, float free_throws_pct,
                             int offensive_rebounds, int defensive_rebounds, int assists, int turnovers, int steals,
                             int blocks, int personal_fouls, int tech_fouls, int points, float avg_points_per_game,
                             int flagrant_fouls, int fouls_drawn) {
        this.id = id;
        this.playerID = playerID;
        this.games_played = games_played;
        this.games_started = games_started;
        this.minutes = minutes;
        this.field_goals_made = field_goals_made;
        this.field_goals_pct = field_goals_pct;
        this.two_points_made = two_points_made;
        this.two_points_pct = two_points_pct;
        this.three_points_made = three_points_made;
        this.three_points_pct = three_points_pct;
        this.free_throws_made = free_throws_made;
        this.free_throws_pct = free_throws_pct;
        this.offensive_rebounds = offensive_rebounds;
        this.defensive_rebounds = defensive_rebounds;
        this.assists = assists;
        this.turnovers = turnovers;
        this.steals = steals;
        this.blocks = blocks;
        this.personal_fouls = personal_fouls;
        this.tech_fouls = tech_fouls;
        this.points = points;
        this.avg_points_per_game = points;
        this.flagrant_fouls = flagrant_fouls;
        this.fouls_drawn = fouls_drawn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getGames_played() {
        return games_played;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public int getGames_started() {
        return games_started;
    }

    public void setGames_started(int games_started) {
        this.games_started = games_started;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getField_goals_made() {
        return field_goals_made;
    }

    public void setField_goals_made(int field_goals_made) {
        this.field_goals_made = field_goals_made;
    }

    public float getField_goals_pct() {
        return field_goals_pct;
    }

    public void setField_goals_pct(float field_goals_pct) {
        this.field_goals_pct = field_goals_pct;
    }

    public int getTwo_points_made() {
        return two_points_made;
    }

    public void setTwo_points_made(int two_points_made) {
        this.two_points_made = two_points_made;
    }

    public float getTwo_points_pct() {
        return two_points_pct;
    }

    public void setTwo_points_pct(float two_points_pct) {
        this.two_points_pct = two_points_pct;
    }

    public int getThree_points_made() {
        return three_points_made;
    }

    public void setThree_points_made(int three_points_made) {
        this.three_points_made = three_points_made;
    }

    public float getThree_points_pct() {
        return three_points_pct;
    }

    public void setThree_points_pct(float three_points_pct) {
        this.three_points_pct = three_points_pct;
    }

    public int getFree_throws_made() {
        return free_throws_made;
    }

    public void setFree_throws_made(int free_throws_made) {
        this.free_throws_made = free_throws_made;
    }

    public float getFree_throws_pct() {
        return free_throws_pct;
    }

    public void setFree_throws_pct(float free_throws_pct) {
        this.free_throws_pct = free_throws_pct;
    }

    public int getOffensive_rebounds() {
        return offensive_rebounds;
    }

    public void setOffensive_rebounds(int offensive_rebounds) {
        this.offensive_rebounds = offensive_rebounds;
    }

    public int getDefensive_rebounds() {
        return defensive_rebounds;
    }

    public void setDefensive_rebounds(int defensive_rebounds) {
        this.defensive_rebounds = defensive_rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getPersonal_fouls() {
        return personal_fouls;
    }

    public void setPersonal_fouls(int personal_fouls) {
        this.personal_fouls = personal_fouls;
    }

    public int getTech_fouls() {
        return tech_fouls;
    }

    public void setTech_fouls(int tech_fouls) {
        this.tech_fouls = tech_fouls;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public float getAvg_points_per_game() {
        return avg_points_per_game;
    }

    public void setAvg_points_per_game(float avg_points_per_game) {
        this.avg_points_per_game = avg_points_per_game;
    }

    public int getFlagrant_fouls() {
        return flagrant_fouls;
    }

    public void setFlagrant_fouls(int flagrant_fouls) {
        this.flagrant_fouls = flagrant_fouls;
    }

    public int getFouls_drawn() {
        return fouls_drawn;
    }

    public void setFouls_drawn(int fouls_drawn) {
        this.fouls_drawn = fouls_drawn;
    }
}
