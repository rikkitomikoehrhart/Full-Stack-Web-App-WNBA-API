package com.rikkitomikoehrhart.league_standard.model;

import java.util.List;

public class GameBoxscore {
    private int id;
    private Game game;
    private Scoring home_scoring;
    private Scoring away_scoring;
    private List<PlayerGameStats> home_top_players;
    private List<PlayerGameStats> away_top_players;


    public GameBoxscore() {}
    public GameBoxscore(int id, Game game, Scoring home_scoring, Scoring away_scoring, List<PlayerGameStats> home_top_players, List<PlayerGameStats> away_top_players) {
        this.id = id;
        this.game = game;
        this.home_scoring = home_scoring;
        this.away_scoring = away_scoring;
        this.home_top_players = home_top_players;
        this.away_top_players = away_top_players;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public Scoring getHome_scoring() {
        return home_scoring;
    }
    public void setHome_scoring(Scoring home_scoring) {
        this.home_scoring = home_scoring;
    }
    public Scoring getAway_scoring() {
        return away_scoring;
    }
    public void setAway_scoring(Scoring away_scoring) {
        this.away_scoring = away_scoring;
    }
    public List<PlayerGameStats> getHome_top_players() {
        return home_top_players;
    }
    public void setHome_top_players(List<PlayerGameStats> home_top_players) {
        this.home_top_players = home_top_players;
    }
    public List<PlayerGameStats> getAway_top_players() {
        return away_top_players;
    }
    public void setAway_top_players(List<PlayerGameStats> away_top_players) {
        this.away_top_players = away_top_players;
    }


}
