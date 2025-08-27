package com.rikkitomikoehrhart.league_standard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikkitomikoehrhart.league_standard.data.GameBoxscoreRepo;
import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoxscoreDataService {
    @Value("${wnba.api.key}")
    private String wnbaAPIKey;

    @Value("${wnba.api.base.url}")
    private String wnbaBaseUrl;

    @Autowired
    private GameBoxscoreRepo gameBoxscoreRepo;

    @Autowired
    private GameRepo gameRepo;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public BoxscoreDataService() {
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
        this.objectMapper = new ObjectMapper();
    }

    public void loadBoxscoreData(String gameID) {
        try {
            String url = String.format("%sgames/%s/boxscore.json", wnbaBaseUrl, gameID);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(30))
                    .header("accept", "application/json")
                    .header("x-api-key", wnbaAPIKey)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                processBoxscoreData(response.body(), gameID);
            } else {
                System.err.println("Boxscore API request failed with status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.err.println("Error fetching boxscore for game " + gameID + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processBoxscoreData(String jsonResponse, String gameID) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            GameBoxscore gameBoxscore = new GameBoxscore();
            Game game = new Game();
            game.setId(getStringFromJson(root, "id"));
            gameBoxscore.setGame(game);

            JsonNode homeNode = root.path("home");
            JsonNode awayNode = root.path("away");


            if (!homeNode.isMissingNode()) {
                gameBoxscore.setHome_scoring(processScoring(homeNode));
                gameBoxscore.setHome_top_players(processTopPlayers(homeNode, game));
            }
            if (!awayNode.isMissingNode()) {
                gameBoxscore.setAway_scoring(processScoring(awayNode));
                gameBoxscore.setAway_top_players(processTopPlayers(awayNode, game));
            }

            if (gameBoxscore != null) {
                gameBoxscoreRepo.addGameBoxscore(gameBoxscore);
            }

        } catch (Exception e) {
            System.err.println("Error processing boxscore data: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private Scoring processScoring(JsonNode node) {
        Scoring scoring = new Scoring();
        JsonNode scoringNode = node.path("scoring");

        for (JsonNode quarter : scoringNode) {
            if (quarter.get("number").asInt() == 1) {
                scoring.setQ1Points(getIntFromJson(quarter, "points"));
            }
            if (quarter.get("number").asInt() == 2) {
                scoring.setQ2Points(getIntFromJson(quarter, "points"));
            }
            if (quarter.get("number").asInt() == 3) {
                scoring.setQ3Points(getIntFromJson(quarter, "points"));
            }
            if (quarter.get("number").asInt() == 4) {
                scoring.setQ4Points(getIntFromJson(quarter, "points"));
            }
        }

        return scoring;
    }

    private List<PlayerGameStats> processTopPlayers(JsonNode node, Game game) {
        List<PlayerGameStats> playerStats = new ArrayList<>();
        JsonNode leadersNode = node.path("leaders");

        for (JsonNode leaders : leadersNode) {
            for (JsonNode player: leaders) {
                PlayerGameStats gameStats = new PlayerGameStats();

                gameStats.setPlayerID(getStringFromJson(player, "id"));
                JsonNode statistics = player.path("statistics");

                gameStats.setMinutesPlayed(getStringFromJson(statistics, "minutes"));
                gameStats.setFieldGoalsMade(getIntFromJson(statistics, "field_goals_made"));
                gameStats.setFieldGoalPercentage(getFloatFromJson(statistics, "field_goals_pct"));
                gameStats.setThreePointsMade(getIntFromJson(statistics, "three_points_made"));
                gameStats.setThreePointPercentage(getFloatFromJson(statistics, "three_points_pct"));
                gameStats.setTwoPointsMade(getIntFromJson(statistics, "two_points_made"));
                gameStats.setTwoPointPercentage(getFloatFromJson(statistics, "two_points_pct"));
                gameStats.setFreeThrowsMade(getIntFromJson(statistics, "free_throws_made"));
                gameStats.setFreeThrowPercentage(getFloatFromJson(statistics, "free_throws_pct"));
                gameStats.setOffensiveRebounds(getIntFromJson(statistics, "offensive_rebounds"));
                gameStats.setDefensiveRebounds(getIntFromJson(statistics, "defensive_rebounds"));
                gameStats.setAssists(getIntFromJson(statistics, "assists"));
                gameStats.setTurnovers(getIntFromJson(statistics, "turnovers"));
                gameStats.setSteals(getIntFromJson(statistics, "steals"));
                gameStats.setBlocks(getIntFromJson(statistics, "blocks"));
                gameStats.setPersonalFouls(getIntFromJson(statistics, "personal_fouls"));
                gameStats.setTechFouls(getIntFromJson(statistics, "tech_fouls"));
                gameStats.setFlagrant_fouls(getIntFromJson(statistics, "flagrant_fouls"));
                gameStats.setPoints(getIntFromJson(statistics, "points"));
                gameStats.setFoulsDrawn(getIntFromJson(statistics, "fouls_drawn"));

                playerStats.add(gameStats);
            }
        }

        return playerStats;
    }



    private String getStringFromJson(JsonNode parentNode, String fieldName) {
        JsonNode fieldNode = parentNode.get(fieldName);
        return (fieldNode != null && !fieldNode.isNull()) ? fieldNode.asText() : "";
    }

    private int getIntFromJson(JsonNode parentNode, String fieldName) {
        JsonNode fieldNode = parentNode.get(fieldName);
        return (fieldNode != null && !fieldNode.isNull()) ? fieldNode.asInt() : 0;
    }

    private float getFloatFromJson(JsonNode parentNode, String fieldName) {
        JsonNode fieldNode = parentNode.get(fieldName);
        return (fieldNode != null && !fieldNode.isNull()) ? (float) fieldNode.asDouble() : 0.0f;
    }

    private boolean getBooleanFromJson(JsonNode parentNode, String fieldName) {
        JsonNode fieldNode = parentNode.get(fieldName);
        return (fieldNode != null && !fieldNode.isNull()) && fieldNode.asBoolean();
    }

    public void loadAllGameBoxscores() throws InterruptedException {
        List<Game> allGames = gameRepo.getCompletedGames();

        int successCount = 0;
        int errorCount = 0;

        for (Game game : allGames) {
            try {
                loadBoxscoreData(game.getId());
                successCount++;
                System.out.println("Loaded boxscore for game: " + game.getId() + " (" + successCount + "/" + allGames.size() + ")");
                Thread.sleep(1000);
            } catch (Exception e) {
                errorCount++;
                System.err.println("Failed to load boxscore for game: " + game.getId() + " - " + e.getMessage());
            }
        }

        System.out.println("Completed: " + successCount + " successful, " + errorCount + " errors out of " + allGames.size() + " total games");
    }
}
