package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.GamesRowMapper;
import com.rikkitomikoehrhart.league_standard.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLGameRepo implements GameRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GamesRowMapper mapper = new GamesRowMapper();

    @Override
    public Game getGameByID(String id) {
        String sql = """
        SELECT g.id as game_id, g.scheduled_date, g.status, g.home_score, g.away_score, g.season_year,
               h.id as home_team_id, h.alias as home_team_alias, h.market as home_team_market, h.name as home_team_name, h.year_founded as home_team_year_founded, h.mascot as home_team_mascot, h.owner as home_team_owner,
               a.id as away_team_id, a.alias as away_team_alias, a.market as away_team_market, a.name as away_team_name, a.year_founded as away_team_year_founded, a.mascot as away_team_mascot, a.owner as away_team_owner
        FROM games g
        JOIN teams h ON g.home_team_id = h.id
        JOIN teams a ON g.away_team_id = a.id
        WHERE g.id = ?;
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.gameRowMapper(), id);
        } catch (Exception e) {
            System.err.println("Error getting Game by id: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Game getNextGameByTeamID(String id) {
        String sql = """
        SELECT g.id as game_id, g.scheduled_date, g.status, g.home_score, g.away_score, g.season_year,
               h.id as home_team_id, h.alias as home_team_alias, h.market as home_team_market, h.name as home_team_name, h.year_founded as home_team_year_founded, h.mascot as home_team_mascot, h.owner as home_team_owner,
               a.id as away_team_id, a.alias as away_team_alias, a.market as away_team_market, a.name as away_team_name, a.year_founded as away_team_year_founded, a.mascot as away_team_mascot, a.owner as away_team_owner
        FROM games g
        JOIN teams h ON g.home_team_id = h.id
        JOIN teams a ON g.away_team_id = a.id
        WHERE (home_team_id = ? OR away_team_id = ?)
        AND scheduled_date >= CURDATE()
        ORDER BY scheduled_date ASC
        LIMIT 1;
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.gameRowMapper(), id, id);
        } catch (Exception e) {
            System.err.println("Error getting next game from team id: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        String sql = """
        SELECT g.id as game_id, g.scheduled_date, g.status, g.home_score, g.away_score, g.season_year,
               h.id as home_team_id, h.alias as home_team_alias, h.market as home_team_market, h.name as home_team_name, h.year_founded as home_team_year_founded, h.mascot as home_team_mascot, h.owner as home_team_owner,
               a.id as away_team_id, a.alias as away_team_alias, a.market as away_team_market, a.name as away_team_name, a.year_founded as away_team_year_founded, a.mascot as away_team_mascot, a.owner as away_team_owner
        FROM games g
        JOIN teams h ON g.home_team_id = h.id
        JOIN teams a ON g.away_team_id = a.id;
        """;

        return jdbcTemplate.query(sql, mapper.gameRowMapper());
    }

    @Override
    public Game addGame(Game game) {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
        String sql = """
        INSERT INTO games (id, home_team_id, away_team_id, scheduled_date, status, home_score, away_score, season_year)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE
            home_team_id = VALUES(home_team_id),
            away_team_id = VALUES(away_team_id),
            scheduled_date = VALUES(scheduled_date),
            status = VALUES(status),
            home_score = VALUES(home_score),
            away_score = VALUES(away_score),
            season_year = VALUES(season_year),
            updated_at = CURRENT_TIMESTAMP
        """;

        try {
            jdbcTemplate.update(sql,
                    game.getId(),
                    game.getHomeTeamID(),
                    game.getAwayTeamID(),
                    game.getScheduled(),
                    game.getStatus(),
                    game.getHomeScore(),
                    game.getAwayScore(),
                    game.getSeasonYear());
        } catch (Exception e) {
            System.err.println("Error saving/updating game: " + e.getMessage());
        } finally {
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
        }

        return game;
    }

    @Override
    public void updateGame(Game game) {
        String sql = """
        UPDATE 'games'
        SET home_team_id = ?, away_team_id = ?, scheduled_date = ?, status = ?, home_score = ?, away_score = ?, season_year = ?
        WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql,
                    game.getHomeTeamID(),
                    game.getAwayTeamID(),
                    game.getScheduled(),
                    game.getStatus(),
                    game.getHomeScore(),
                    game.getAwayScore(),
                    game.getSeasonYear(),
                    game.getId());
        } catch (Exception e) {
            System.err.println("Error updating game: " + e.getMessage());
        }
    }

    @Override
    public void deleteGame(String id) {
        String sql = """
        DELETE FROM games WHERE id = ?;
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting game: " + e.getMessage());
        }
    }

    @Override
    public List<Game> getCompletedGames() {
        String sql = """
        SELECT g.id as game_id, g.scheduled_date, g.status, g.home_score, g.away_score, g.season_year,
               h.id as home_team_id, h.alias as home_team_alias, h.market as home_team_market, h.name as home_team_name, h.year_founded as home_team_year_founded, h.mascot as home_team_mascot, h.owner as home_team_owner,
               a.id as away_team_id, a.alias as away_team_alias, a.market as away_team_market, a.name as away_team_name, a.year_founded as away_team_year_founded, a.mascot as away_team_mascot, a.owner as away_team_owner
        FROM games g
        JOIN teams h ON g.home_team_id = h.id
        JOIN teams a ON g.away_team_id = a.id
        WHERE g.status IN ('complete', 'closed')
        ORDER BY g.scheduled_date DESC;
        """;

        return jdbcTemplate.query(sql, mapper.gameRowMapper());
    }
}
