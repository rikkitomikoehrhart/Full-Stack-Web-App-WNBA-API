package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.PlayerSeasonStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class PlayerSeasonStatsRowMapper {

    public RowMapper<PlayerSeasonStats> playerSeasonStatsRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            PlayerSeasonStats stats = new PlayerSeasonStats();
            stats.setId(rs.getInt("id"));
            stats.setPlayerID(rs.getString("player_id"));
            stats.setGames_played(rs.getInt("games_played"));
            stats.setGames_started(rs.getInt("games_started"));
            stats.setMinutes(rs.getInt("minutes"));
            stats.setField_goals_made(rs.getInt("field_goals_made"));
            stats.setField_goals_pct((float) rs.getDouble("field_goals_pct"));
            stats.setTwo_points_made(rs.getInt("two_points_made"));
            stats.setTwo_points_pct((float) rs.getDouble("two_points_pct"));
            stats.setThree_points_made(rs.getInt("three_points_made"));
            stats.setThree_points_pct((float) rs.getDouble("three_points_pct"));
            stats.setFree_throws_made(rs.getInt("free_throws_made"));
            stats.setFree_throws_pct((float) rs.getDouble("free_throws_pct"));
            stats.setOffensive_rebounds(rs.getInt("offensive_rebounds"));
            stats.setDefensive_rebounds(rs.getInt("defensive_rebounds"));
            stats.setAssists(rs.getInt("assists"));
            stats.setTurnovers(rs.getInt("turnovers"));
            stats.setSteals(rs.getInt("steals"));
            stats.setBlocks(rs.getInt("blocks"));
            stats.setPersonal_fouls(rs.getInt("personal_fouls"));
            stats.setTech_fouls(rs.getInt("tech_fouls"));
            stats.setPoints(rs.getInt("points"));
            stats.setAvg_points_per_game((float) rs.getDouble("avg_points_per_game"));
            stats.setFlagrant_fouls(rs.getInt("flagrant_fouls"));
            stats.setFouls_drawn(rs.getInt("fouls_drawn"));


            return stats;
        };
    }
}
