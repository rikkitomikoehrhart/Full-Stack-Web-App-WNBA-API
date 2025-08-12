package com.rikkitomikoehrhart.league_standard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikkitomikoehrhart.league_standard.model.Game;
import com.rikkitomikoehrhart.league_standard.model.Team;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class APIService {
    @Value("${wnba.api.key}")
    private String wnbaAPIKey;

    @Value("${wnba.api.base.url}")
    private String wnbaBaseUrl;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public APIService() {
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
        this.objectMapper = new ObjectMapper();
    }

    public List<Game> fetchWNBAGames(int year, String seasonType) {
        try {
            String url = String.format("%sgames/%s/%s/schedule.json", wnbaBaseUrl, year, seasonType);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(30))
                    .header("accept", "application/json")
                    .header("x-api-key", wnbaAPIKey)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return parseGamesFromJSON(response.body());
            } else {
                System.err.println("API request failed with status: " + response.statusCode());
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.err.println("Error fetching games from API: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Game> parseGamesFromJSON(String jsonResponse) {
        List<Game> games = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode gamesNode = root.get("games");

            if (gamesNode != null && gamesNode.isArray()) {
                for (JsonNode gameNode : gamesNode) {
                    Game game = mapJSONToGame(gameNode);
                    if (game != null) {
                        games.add(game);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing games JSON: " + e.getMessage());
        }

        return games;
    }

    private Game mapJSONToGame(JsonNode gameNode) {
        try {
            Game game = new Game();

            game.setId(gameNode.get("id").asText());
            game.setStatus(gameNode.get("status").asText());
            game.setSeasonYear(Integer.parseInt(gameNode.get("scheduled").asText().substring(0, 4)));

            JsonNode homeScoreNode = gameNode.get("home_points");
            JsonNode awayScoreNode = gameNode.get("away_points");
            game.setHomeScore(homeScoreNode != null ? homeScoreNode.asInt() : 0);
            game.setAwayScore(awayScoreNode != null ? awayScoreNode.asInt() : 0);

            JsonNode homeTeamNode = gameNode.get("home");
            JsonNode awayTeamNode = gameNode.get("away");
            Team homeTeam = new Team();
            Team awayTeam = new Team();

            if (homeTeamNode != null) {
                homeTeam.setId(homeTeamNode.get("id").asText());
                game.setHomeTeam(homeTeam);
            }
            if (awayTeamNode != null) {
                awayTeam.setId(awayTeamNode.get("id").asText());
                game.setAwayTeam(awayTeam);
            }

            String scheduledStr = gameNode.get("scheduled").asText();
            LocalDate scheduledDate = LocalDate.parse(scheduledStr.substring(0, 10));

            return game;
        } catch (Exception e) {
            System.err.println("Error mapping game JSON: " + e.getMessage());
            return null;
        }
    }

    public List<Game> fetchAllWNBAGames(int year) throws InterruptedException {
        List<Game> allGames = new ArrayList<>();

        allGames.addAll(fetchWNBAGames(year, "PRE"));
        Thread.sleep(2000);

        allGames.addAll(fetchWNBAGames(year, "REG"));
        Thread.sleep(2000);

        allGames.addAll(fetchWNBAGames(year, "CC"));
        Thread.sleep(2000);

        allGames.addAll(fetchWNBAGames(year, "PST"));

        return allGames;
    }

    public String getWnbaAPIKey() {
        return wnbaAPIKey;
    }

    public String getWnbaBaseUrl() {
        return wnbaBaseUrl;
    }
}
