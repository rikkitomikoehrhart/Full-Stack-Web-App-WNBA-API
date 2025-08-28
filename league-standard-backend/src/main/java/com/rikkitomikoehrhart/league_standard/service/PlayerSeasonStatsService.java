package com.rikkitomikoehrhart.league_standard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikkitomikoehrhart.league_standard.data.PlayerSeasonStatsRepo;
import com.rikkitomikoehrhart.league_standard.model.PlayerSeasonStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class PlayerSeasonStatsService {
    @Value("${wnba.api.key}")
    private String wnbaAPIKey;

    @Value("${wnba.api.base.url}")
    private String wnbaBaseUrl;

    @Autowired
    private PlayerSeasonStatsRepo playerSeasonStatsRepo;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public PlayerSeasonStatsService() {
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
        this.objectMapper = new ObjectMapper();
    }

    public void loadPlayerSeasonStatsByTeam(String teamID) {
        try {
            String url = String.format("%sseasons/2025/REG/teams/%s/statistics.json", wnbaBaseUrl, teamID);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(30))
                    .header("accept", "application/json")
                    .header("x-api-key", wnbaAPIKey)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                processStatsData(response.body(), teamID);
            } else {
                System.err.println("Player Season Stats API request failed with status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.err.println("Error fetching Player Season Stats for team " + teamID + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processStatsData(String jsonResponse, String teamID) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode playerRoot = root.path("players");

            for (JsonNode player : playerRoot) {
                PlayerSeasonStats stats = new PlayerSeasonStats();
                stats.setPlayerID(player.get("id").asText());

                JsonNode totals = player.path("total");

                stats.setGames_played(totals.get("games_played").asInt());
                stats.setGames_started(totals.get("games_started").asInt());
                stats.setMinutes(totals.get("minutes").asInt());
                stats.setField_goals_made(totals.get("field_goals_made").asInt());
                stats.setField_goals_pct((float) (totals.get("field_goals_pct").asDouble() * 100));
                stats.setTwo_points_made(totals.get("two_points_made").asInt());
                stats.setTwo_points_pct((float) (totals.get("two_points_pct").asDouble() * 100));
                stats.setThree_points_made(totals.get("three_points_made").asInt());
                stats.setThree_points_pct((float) (totals.get("three_points_pct").asDouble() * 100));
                stats.setFree_throws_made(totals.get("free_throws_made").asInt());
                stats.setFree_throws_pct((float) (totals.get("free_throws_pct").asDouble() * 100));
                stats.setOffensive_rebounds(totals.get("offensive_rebounds").asInt());
                stats.setDefensive_rebounds(totals.get("defensive_rebounds").asInt());
                stats.setAssists(totals.get("assists").asInt());
                stats.setTurnovers(totals.get("turnovers").asInt());
                stats.setSteals(totals.get("steals").asInt());
                stats.setBlocks(totals.get("blocks").asInt());
                stats.setPersonal_fouls(totals.get("personal_fouls").asInt());
                stats.setTech_fouls(totals.get("tech_fouls").asInt());
                stats.setPoints(totals.get("points").asInt());
                stats.setFlagrant_fouls(totals.get("flagrant_fouls").asInt());
                stats.setFouls_drawn(totals.get("fouls_drawn").asInt());

                JsonNode averages = player.path("average");

                stats.setAvg_points_per_game((float) averages.get("points").asDouble());

                playerSeasonStatsRepo.addPlayerSeasonStats(stats);
            }


        } catch (Exception e) {
            System.err.println("Error processing player season stats data: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
