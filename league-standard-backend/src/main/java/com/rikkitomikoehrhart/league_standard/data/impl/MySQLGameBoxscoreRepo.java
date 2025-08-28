package com.rikkitomikoehrhart.league_standard.data.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rikkitomikoehrhart.league_standard.data.GameBoxscoreRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.GameBoxscoreRowMapper;
import com.rikkitomikoehrhart.league_standard.model.GameBoxscore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class MySQLGameBoxscoreRepo implements GameBoxscoreRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameBoxscoreRowMapper mapper = new GameBoxscoreRowMapper();
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public GameBoxscore getGameBoxscoreByID(int id) {
        String sql = """
        SELECT gb.id, gb.home_scoring, gb.away_scoring, gb.home_top_players, gb.away_top_players,
               g.id as game_id, g.scheduled_date, g.status, g.home_score, g.away_score, g.season_year,
               ht.id as home_team_id, ht.alias as home_team_alias, ht.market as home_team_market, 
               ht.name as home_team_name, ht.year_founded as home_team_year_founded, 
               ht.mascot as home_team_mascot, ht.owner as home_team_owner,
               aw.id as away_team_id, aw.alias as away_team_alias, aw.market as away_team_market, 
               aw.name as away_team_name, aw.year_founded as away_team_year_founded, 
               aw.mascot as away_team_mascot, aw.owner as away_team_owner
        FROM game_boxscores gb
        JOIN games g ON gb.game_id = g.id
        JOIN teams ht ON g.home_team_id = ht.id
        JOIN teams aw ON g.away_team_id = aw.id
        WHERE gb.id = ?
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.gameBoxscoreRowMapper(), String.valueOf(id));
        } catch (Exception e) {
            System.err.println("Boxscore not found with id: " + id);
            return null;
        }
    }

    @Override
    public List<GameBoxscore> getBoxscoresByGameID(String gameID) {
        String sql = """
        SELECT gb.id, gb.home_scoring, gb.away_scoring, gb.home_top_players, gb.away_top_players,
               g.id as game_id, g.scheduled_date, g.status, g.home_score, g.away_score, g.season_year,
               ht.id as home_team_id, ht.alias as home_team_alias, ht.market as home_team_market, 
               ht.name as home_team_name, ht.year_founded as home_team_year_founded, 
               ht.mascot as home_team_mascot, ht.owner as home_team_owner,
               aw.id as away_team_id, aw.alias as away_team_alias, aw.market as away_team_market, 
               aw.name as away_team_name, aw.year_founded as away_team_year_founded, 
               aw.mascot as away_team_mascot, aw.owner as away_team_owner
        FROM game_boxscores gb
        JOIN games g ON gb.game_id = g.id
        JOIN teams ht ON g.home_team_id = ht.id
        JOIN teams aw ON g.away_team_id = aw.id
        WHERE g.id = ?
        """;

        return jdbcTemplate.query(sql, mapper.gameBoxscoreRowMapper(), gameID);
    }

    @Override
    public GameBoxscore getBoxscoreByGameAndTeam(String gameId, String teamId) {
        String sql = """
        SELECT gb.id, gb.home_scoring, gb.away_scoring, gb.home_top_players, gb.away_top_players,
               g.id as game_id, g.scheduled_date, g.status, g.home_score, g.away_score, g.season_year,
               ht.id as home_team_id, ht.alias as home_team_alias, ht.market as home_team_market, 
               ht.name as home_team_name, ht.year_founded as home_team_year_founded, 
               ht.mascot as home_team_mascot, ht.owner as home_team_owner,
               aw.id as away_team_id, aw.alias as away_team_alias, aw.market as away_team_market, 
               aw.name as away_team_name, aw.year_founded as away_team_year_founded, 
               aw.mascot as away_team_mascot, aw.owner as away_team_owner
        FROM game_boxscores gb
        JOIN games g ON gb.game_id = g.id
        JOIN teams ht ON g.home_team_id = ht.id
        JOIN teams aw ON g.away_team_id = aw.id
        WHERE g.id = ? AND (ht.id = ? OR aw.id = ?)
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.gameBoxscoreRowMapper(), gameId, teamId);
        } catch (Exception e) {
            System.err.println("Boxscore not found for game: " + gameId + " and team: " + teamId);
            return null;
        }
    }

    @Override
    public List<GameBoxscore> getAllBoxscores() {
        String sql = """
        SELECT gb.id, gb.home_scoring, gb.away_scoring, gb.home_top_players, gb.away_top_players,
               g.id as game_id, g.scheduled_date, g.status, g.home_score, g.away_score, g.season_year,
               ht.id as home_team_id, ht.alias as home_team_alias, ht.market as home_team_market, 
               ht.name as home_team_name, ht.year_founded as home_team_year_founded, 
               ht.mascot as home_team_mascot, ht.owner as home_team_owner,
               aw.id as away_team_id, aw.alias as away_team_alias, aw.market as away_team_market, 
               aw.name as away_team_name, aw.year_founded as away_team_year_founded, 
               aw.mascot as away_team_mascot, aw.owner as away_team_owner
        FROM game_boxscores gb
        JOIN games g ON gb.game_id = g.id
        JOIN teams ht ON g.home_team_id = ht.id
        JOIN teams aw ON g.away_team_id = aw.id
        """;

        return jdbcTemplate.query(sql, mapper.gameBoxscoreRowMapper());
    }

    @Override
    public GameBoxscore addGameBoxscore(GameBoxscore boxscore) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        String sql = """
        INSERT INTO game_boxscores (game_id, home_scoring, away_scoring, home_top_players, away_top_players)
        VALUES (?, ?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE
            game_id = VALUES(game_id),
            home_scoring = VALUES(home_scoring),
            away_scoring = VALUES(away_scoring),
            home_top_players = VALUES(home_top_players),
            away_top_players = VALUES(away_top_players),
            updated_at = CURRENT_TIMESTAMP
        """;

        try {
            String homeScoringJson = objectMapper.writeValueAsString(boxscore.getHome_scoring());
            String awayScoringJson = objectMapper.writeValueAsString(boxscore.getAway_scoring());
            String homeTopPlayersJson = objectMapper.writeValueAsString(boxscore.getHome_top_players());
            String awayTopPlayersJson = objectMapper.writeValueAsString(boxscore.getAway_top_players());

            jdbcTemplate.update(sql,
                    boxscore.getGame().getId(),
                    homeScoringJson,
                    awayScoringJson,
                    homeTopPlayersJson,
                    awayTopPlayersJson);
        } catch (Exception e) {
            System.err.println("Error adding/updating game boxscore: " + e.getMessage());
        }

        return boxscore;
    }

    @Override
    public void updateGameBoxscore(GameBoxscore boxscore) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        String sql = """
        UPDATE game_boxscores SET
            game_id = ?, home_scoring = ?, away_scoring = ?, home_top_players = ?, away_top_players = ?
        WHERE id = ?
        """;

        try {
            String homeScoringJson = objectMapper.writeValueAsString(boxscore.getHome_scoring());
            String awayScoringJson = objectMapper.writeValueAsString(boxscore.getAway_scoring());
            String homeTopPlayersJson = objectMapper.writeValueAsString(boxscore.getHome_top_players());
            String awayTopPlayersJson = objectMapper.writeValueAsString(boxscore.getAway_top_players());

            jdbcTemplate.update(sql,
                    boxscore.getGame().getId(),
                    homeScoringJson,
                    awayScoringJson,
                    homeTopPlayersJson,
                    awayTopPlayersJson,
                    String.valueOf(boxscore.getId()));
        } catch (Exception e) {
            System.err.println("Error updating game boxscore: " + e.getMessage());
        }
    }

    @Override
    public void deleteGameBoxscore(int id) {
        String sql = "DELETE FROM game_boxscores WHERE id = ?";

        try {
            jdbcTemplate.update(sql, String.valueOf(id));
        } catch (Exception e) {
            System.err.println("Error deleting game boxscore: " + e.getMessage());
        }
    }
}
