package com.rikkitomikoehrhart.league_standard.data;


import com.rikkitomikoehrhart.league_standard.model.PlayerSeasonStats;

import java.util.List;

public interface PlayerSeasonStatsRepo {
    public PlayerSeasonStats getPlayerSeasonStatsByID(int id);
    public List<PlayerSeasonStats> getAllPlayerSeasonStats();
    public PlayerSeasonStats addPlayerSeasonStats(PlayerSeasonStats stats);
    public void updatePlayerSeasonStats(PlayerSeasonStats stats);
    public void deletePlayerSeasonStats(int id);


}
