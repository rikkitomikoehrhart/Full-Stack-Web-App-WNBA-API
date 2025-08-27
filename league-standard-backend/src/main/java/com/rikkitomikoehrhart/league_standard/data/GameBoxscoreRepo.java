package com.rikkitomikoehrhart.league_standard.data;

import com.rikkitomikoehrhart.league_standard.model.GameBoxscore;

import java.util.List;

public interface GameBoxscoreRepo {
    public GameBoxscore getGameBoxscoreByID(int id);
    public List<GameBoxscore> getBoxscoresByGameID(String gameID);
    public GameBoxscore getBoxscoreByGameAndTeam(String gameID, String teamID);
    public List<GameBoxscore> getAllBoxscores();
    public GameBoxscore addGameBoxscore(GameBoxscore boxscore);
    public void updateGameBoxscore(GameBoxscore boxscore);
    public void deleteGameBoxscore(int id);
}

