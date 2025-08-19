package com.rikkitomikoehrhart.league_standard.data;

import com.rikkitomikoehrhart.league_standard.model.Player;

import java.util.List;

public interface PlayerRepo {
    public Player getPlayerByID(String id);
    public List<Player> getAllPlayers();
    public List<Player> getPlayersByTeam(String id);
    public Player addPlayer(Player player);
    public void updatePlayer(Player player);
    public void deletePlayer(String id);
}
