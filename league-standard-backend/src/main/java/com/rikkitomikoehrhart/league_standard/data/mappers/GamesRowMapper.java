package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.Game;
import com.rikkitomikoehrhart.league_standard.model.Team;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GamesRowMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();

        game.setId(rs.getString("game_id"));

        Team homeTeam = new Team();
        homeTeam.setId(rs.getString("home_team_id"));
        homeTeam.setAlias(rs.getString("home_team_alias"));
        homeTeam.setMarket(rs.getString("home_team_market"));
        homeTeam.setName(rs.getString("home_team_name"));
        homeTeam.setYearFounded(rs.getInt("home_team_year_founded"));
        homeTeam.setMascot(rs.getString("home_team_mascot"));
        homeTeam.setOwner(rs.getString("home_team_owner"));
        game.setHomeTeam(homeTeam);

        Team awayTeam = new Team();
        awayTeam.setId(rs.getString("away_team_id"));
        awayTeam.setAlias(rs.getString("away_team_alias"));
        awayTeam.setMarket(rs.getString("away_team_market"));
        awayTeam.setName(rs.getString("away_team_name"));
        awayTeam.setYearFounded(rs.getInt("away_team_year_founded"));
        awayTeam.setMascot(rs.getString("away_team_mascot"));
        awayTeam.setOwner(rs.getString("away_team_owner"));
        game.setAwayTeam(awayTeam);

        game.setScheduled(rs.getDate("scheduled_date").toLocalDate());
        game.setStatus(rs.getString("status"));
        game.setHomeScore(rs.getInt("home_score"));
        game.setAwayScore(rs.getInt("away_score"));
        game.setSeasonYear(rs.getInt("season_year"));


        return game;
    }
}
