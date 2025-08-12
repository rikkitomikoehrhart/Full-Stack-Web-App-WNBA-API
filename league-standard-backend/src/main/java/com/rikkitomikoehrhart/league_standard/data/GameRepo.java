package com.rikkitomikoehrhart.league_standard.data;

import com.rikkitomikoehrhart.league_standard.model.Game;

import java.util.List;

public interface GameRepo {
    public Game getGameByID(String id);
    public List<Game> getAllGames();
    public Game addGame(Game game);
    public void updateGame(Game game);
    public void deleteGame(String id);
}
