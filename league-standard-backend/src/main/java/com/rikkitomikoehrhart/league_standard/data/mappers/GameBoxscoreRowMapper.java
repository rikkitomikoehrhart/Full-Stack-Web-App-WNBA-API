package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikkitomikoehrhart.league_standard.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameBoxscoreRowMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();


    public RowMapper<GameBoxscore> gameBoxscoreRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            GameBoxscore boxscore = new GameBoxscore();

            boxscore.setId(rs.getInt("id"));

            Game game = new Game();
            game.setId(rs.getString("game_id"));

            Team home = new Team();
            home.setId(rs.getString("home_team_id"));
            home.setAlias(rs.getString("home_team_alias"));
            home.setMarket(rs.getString("home_team_market"));
            home.setName(rs.getString("home_team_name"));
            home.setYearFounded(rs.getInt("home_team_year_founded"));
            home.setMascot(rs.getString("home_team_mascot"));
            home.setOwner(rs.getString("home_team_owner"));

            game.setHomeTeam(home);


            Team away = new Team();
            away.setId(rs.getString("away_team_id"));
            away.setAlias(rs.getString("away_team_alias"));
            away.setMarket(rs.getString("away_team_market"));
            away.setName(rs.getString("away_team_name"));
            away.setYearFounded(rs.getInt("away_team_year_founded"));
            away.setMascot(rs.getString("away_team_mascot"));
            away.setOwner(rs.getString("away_team_owner"));

            game.setAwayTeam(away);


            game.setScheduled(LocalDate.parse(rs.getString("scheduled_date").substring(0, 10)));
            game.setStatus(rs.getString("status"));
            game.setHomeScore(rs.getInt("home_score"));
            game.setAwayScore(rs.getInt("away_score"));
            game.setSeasonYear(rs.getInt("season_year"));


            boxscore.setGame(game);


            Scoring homeScoring = new Scoring();
            Scoring awayScoring = new Scoring();

            String homeScoreJSON = rs.getString("home_scoring");
            String awayScoreJSON = rs.getString("away_scoring");


            if (homeScoreJSON != null) {
                try {
                    homeScoring = objectMapper.readValue(homeScoreJSON, Scoring.class);
                } catch (Exception e) {
                    System.err.println("Error parsing Home Scoring JSON details: " + e.getMessage());
                }
            }

            if (awayScoreJSON != null) {
                try {
                    awayScoring = objectMapper.readValue(awayScoreJSON, Scoring.class);
                } catch (Exception e) {
                    System.err.println("Error parsing Away Scoring JSON details: " + e.getMessage());
                }
            }

            boxscore.setHome_scoring(homeScoring);
            boxscore.setAway_scoring(awayScoring);

            List<PlayerGameStats> homeTopPlayers = new ArrayList<>();
            List<PlayerGameStats> awayTopPlayers = new ArrayList<>();

            try {
                JsonNode homePlayersNode = objectMapper.readTree(rs.getString("home_top_players"));

                for (JsonNode playerNode : homePlayersNode) {
                    PlayerGameStats stats = new PlayerGameStats();
                    stats.setPlayerID(playerNode.get("playerID").asText());

                    stats.setMinutesPlayed(playerNode.get("minutesPlayed").asText());
                    stats.setFieldGoalsMade(playerNode.get("fieldGoalsMade").asInt());
                    stats.setFieldGoalPercentage((float) playerNode.get("fieldGoalPercentage").asDouble());
                    stats.setThreePointsMade(playerNode.get("threePointsMade").asInt());
                    stats.setThreePointPercentage((float) playerNode.get("threePointPercentage").asDouble());
                    stats.setTwoPointsMade(playerNode.get("twoPointsMade").asInt());
                    stats.setTwoPointPercentage((float) playerNode.get("twoPointPercentage").asDouble());
                    stats.setFreeThrowsMade(playerNode.get("freeThrowsMade").asInt());
                    stats.setFreeThrowPercentage((float) playerNode.get("freeThrowPercentage").asDouble());
                    stats.setOffensiveRebounds(playerNode.get("offensiveRebounds").asInt());
                    stats.setDefensiveRebounds(playerNode.get("defensiveRebounds").asInt());
                    stats.setAssists(playerNode.get("assists").asInt());
                    stats.setTurnovers(playerNode.get("turnovers").asInt());
                    stats.setSteals(playerNode.get("steals").asInt());
                    stats.setBlocks(playerNode.get("blocks").asInt());
                    stats.setPersonalFouls(playerNode.get("personalFouls").asInt());
                    stats.setTechFouls(playerNode.get("techFouls").asInt());
                    stats.setFlagrant_fouls(playerNode.get("flagrant_fouls").asInt());
                    stats.setPoints(playerNode.get("points").asInt());
                    stats.setFoulsDrawn(playerNode.get("foulsDrawn").asInt());

                    homeTopPlayers.add(stats);
                }
            } catch (Exception e) {
                System.err.println("Error parsing Home Top Players Json: " + e.getMessage());
            }

            boxscore.setHome_top_players(homeTopPlayers);

            try {
                JsonNode awayPlayersNode = objectMapper.readTree(rs.getString("away_top_players"));

                for (JsonNode playerNode : awayPlayersNode) {
                    PlayerGameStats stats = new PlayerGameStats();
                    stats.setPlayerID(playerNode.get("playerID").asText());

                    stats.setMinutesPlayed(playerNode.get("minutesPlayed").asText());
                    stats.setFieldGoalsMade(playerNode.get("fieldGoalsMade").asInt());
                    stats.setFieldGoalPercentage((float) playerNode.get("fieldGoalPercentage").asDouble());
                    stats.setThreePointsMade(playerNode.get("threePointsMade").asInt());
                    stats.setThreePointPercentage((float) playerNode.get("threePointPercentage").asDouble());
                    stats.setTwoPointsMade(playerNode.get("twoPointsMade").asInt());
                    stats.setTwoPointPercentage((float) playerNode.get("twoPointPercentage").asDouble());
                    stats.setFreeThrowsMade(playerNode.get("freeThrowsMade").asInt());
                    stats.setFreeThrowPercentage((float) playerNode.get("freeThrowPercentage").asDouble());
                    stats.setOffensiveRebounds(playerNode.get("offensiveRebounds").asInt());
                    stats.setDefensiveRebounds(playerNode.get("defensiveRebounds").asInt());
                    stats.setAssists(playerNode.get("assists").asInt());
                    stats.setTurnovers(playerNode.get("turnovers").asInt());
                    stats.setSteals(playerNode.get("steals").asInt());
                    stats.setBlocks(playerNode.get("blocks").asInt());
                    stats.setPersonalFouls(playerNode.get("personalFouls").asInt());
                    stats.setTechFouls(playerNode.get("techFouls").asInt());
                    stats.setFlagrant_fouls(playerNode.get("flagrant_fouls").asInt());
                    stats.setPoints(playerNode.get("points").asInt());
                    stats.setFoulsDrawn(playerNode.get("foulsDrawn").asInt());

                    awayTopPlayers.add(stats);
                }
            } catch (Exception e) {
                System.err.println("Error parsing Away Top Players Json: " + e.getMessage());
            }

            boxscore.setAway_top_players(awayTopPlayers);

            return boxscore;
        };
    }
}
