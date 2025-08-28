package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.PlayerSeasonStatsRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.PlayerSeasonStatsRowMapper;
import com.rikkitomikoehrhart.league_standard.model.PlayerSeasonStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLPlayerSeasonStatsRepo implements PlayerSeasonStatsRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PlayerSeasonStatsRowMapper mapper = new PlayerSeasonStatsRowMapper();


    @Override
    public PlayerSeasonStats getPlayerSeasonStatsByID(int id) {
        String sql = """
        SELECT * FROM player_season_stats
        WHERE id = ?
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.playerSeasonStatsRowMapper(), String.valueOf(id));
        } catch (Exception e) {
            System.err.println("Player Season Stats not found with id: " + id);
            return null;
        }
    }

    @Override
    public List<PlayerSeasonStats> getAllPlayerSeasonStats() {
        String sql = """
        SELECT * FROM player_season_stats
        """;


        return jdbcTemplate.query(sql, mapper.playerSeasonStatsRowMapper());
    }

    @Override
    public PlayerSeasonStats addPlayerSeasonStats(PlayerSeasonStats stats) {
        String sql = """
        INSERT INTO player_season_stats (player_id, games_played, games_started, minutes,
                                         field_goals_made, field_goals_pct, two_points_made,
                                         two_points_pct, three_points_made, three_points_pct,
                                         free_throws_made, free_throws_pct, offensive_rebounds,
                                         defensive_rebounds, assists, turnovers, steals, blocks, 
                                         personal_fouls, tech_fouls, points, avg_points_per_game,
                                         flagrant_fouls, fouls_drawn)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE
            player_id = VALUES(player_id),
            games_played = VALUES(games_played),
            games_started = VALUES(games_started),
            minutes = VALUES(minutes),
            field_goals_made = VALUES(field_goals_made),
            field_goals_pct = VALUES(field_goals_pct),
            two_points_made = VALUES(two_points_made),
            two_points_pct = VALUES(two_points_pct),
            three_points_made = VALUES(three_points_made),
            three_points_pct = VALUES(three_points_pct),
            free_throws_made = VALUES(free_throws_made),
            free_throws_pct = VALUES(free_throws_pct),
            offensive_rebounds = VALUES(offensive_rebounds),
            defensive_rebounds = VALUES(defensive_rebounds),
            assists = VALUES(assists),
            turnovers = VALUES(turnovers),
            steals = VALUES(steals),
            blocks = VALUES(blocks),
            personal_fouls = VALUES(personal_fouls),
            tech_fouls = VALUES(tech_fouls),
            points = VALUES(points),
            avg_points_per_game = VALUES(avg_points_per_game),
            flagrant_fouls = VALUES(flagrant_fouls),
            fouls_drawn = VALUES(fouls_drawn),
            updated_at = CURRENT_TIMESTAMP
        """;

        try {
            jdbcTemplate.update(sql,
                    stats.getPlayerID(),
                    stats.getGames_played(),
                    stats.getGames_started(),
                    stats.getMinutes(),
                    stats.getField_goals_made(),
                    stats.getField_goals_pct(),
                    stats.getTwo_points_made(),
                    stats.getTwo_points_pct(),
                    stats.getThree_points_made(),
                    stats.getThree_points_pct(),
                    stats.getFree_throws_made(),
                    stats.getFree_throws_pct(),
                    stats.getOffensive_rebounds(),
                    stats.getDefensive_rebounds(),
                    stats.getAssists(),
                    stats.getTurnovers(),
                    stats.getSteals(),
                    stats.getBlocks(),
                    stats.getPersonal_fouls(),
                    stats.getTech_fouls(),
                    stats.getPoints(),
                    stats.getAvg_points_per_game(),
                    stats.getFlagrant_fouls(),
                    stats.getFouls_drawn());
        } catch (Exception e) {
            System.err.println("Error adding/updating Player Seasonal Stats: " + e.getMessage());
        }

        return stats;
    }

    @Override
    public void updatePlayerSeasonStats(PlayerSeasonStats stats) {
        String sql = """
        UPDATE player_season_stats SET
            player_id = ?, games_played = ?, games_started = ?, minutes = ?, field_goals_made = ?, 
            field_goals_pct = ?, two_points_made = ?, two_points_pct = ?, three_points_made = ?, 
            three_points_pct = ?, free_throws_made = ?, free_throws_pct = ?, offensive_rebounds = ?,
            defensive_rebounds = ?, assists = ?, turnovers = ?, steals = ?, blocks = ?, 
            personal_fouls = ?, tech_fouls = ?, points = ?, avg_points_per_game = ?, 
            flagrant_fouls = ?, fouls_drawn = ?)
        WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql,
                    stats.getPlayerID(),
                    stats.getGames_played(),
                    stats.getGames_started(),
                    stats.getMinutes(),
                    stats.getField_goals_made(),
                    stats.getField_goals_pct(),
                    stats.getTwo_points_made(),
                    stats.getTwo_points_pct(),
                    stats.getThree_points_made(),
                    stats.getThree_points_pct(),
                    stats.getFree_throws_made(),
                    stats.getFree_throws_pct(),
                    stats.getOffensive_rebounds(),
                    stats.getDefensive_rebounds(),
                    stats.getAssists(),
                    stats.getTurnovers(),
                    stats.getSteals(),
                    stats.getBlocks(),
                    stats.getPersonal_fouls(),
                    stats.getTech_fouls(),
                    stats.getPoints(),
                    stats.getAvg_points_per_game(),
                    stats.getFlagrant_fouls(),
                    stats.getFouls_drawn(),
                    stats.getId());
        } catch (Exception e) {
            System.err.println("Error updating Player Seasonal Stats: " + e.getMessage());
        }
    }

    @Override
    public void deletePlayerSeasonStats(int id) {
        String sql = """
        DELETE FROM player_season_stats WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql, String.valueOf(id));
        } catch (Exception e) {
            System.err.println("Error deleting player seasonal stats: " + e.getMessage());
        }
    }
}
