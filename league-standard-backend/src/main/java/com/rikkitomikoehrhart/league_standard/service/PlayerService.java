package com.rikkitomikoehrhart.league_standard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikkitomikoehrhart.league_standard.data.PlayerRepo;
import com.rikkitomikoehrhart.league_standard.model.Player;
import com.rikkitomikoehrhart.league_standard.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlayerService {
    @Value("${wnba.api.key}")
    private String wnbaAPIKey;

    @Value("${wnba.api.base.url}")
    private String wnbaBaseUrl;

    @Autowired
    private PlayerRepo playerRepo;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;


    public PlayerService() {
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
        this.objectMapper = new ObjectMapper();
    }

    public List<Player> fetchPlayers(String teamID) {
        try {
            String url = String.format("%steams/%s/profile.json", wnbaBaseUrl, teamID);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(30))
                    .header("accept", "application/json")
                    .header("x-api-key", wnbaAPIKey)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return processPlayerData(response.body(), teamID);
            } else {
                System.err.println("API request failed with status: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error fetching team profile for " + teamID + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private List<Player> processPlayerData(String jsonResponse, String teamID) {
        try {
            List<Player> playersList = new ArrayList<>();

            JsonNode root = objectMapper.readTree(jsonResponse);

            JsonNode players = root.path("players");

            for (JsonNode player : players) {
                Player p = new Player();

                p.setId(getStringFromJson(player, "id"));

                Team team = new Team();
                team.setId(teamID);
                p.setTeam(team);

                p.setStatus(getStringFromJson(player, "status"));
                p.setFirst_name(getStringFromJson(player, "first_name"));
                p.setLast_name(getStringFromJson(player, "last_name"));

                System.out.println("Player: " + p.getFirst_name() + " " + p.getLast_name());

                p.setHeight(getIntFromJson(player, "height"));
                p.setPosition(getStringFromJson(player, "position"));
                p.setJersey_number(parseIntFromJson(player, "jersey_number"));
                p.setExperience(parseIntFromJson(player, "experience"));
                p.setCollege(getStringFromJson(player, "college"));
                p.setBirth_place(getStringFromJson(player, "birth_place"));
                p.setBirthdate(LocalDate.parse(getStringFromJson(player, "birthdate")));
                p.setRookie_year(getIntFromJson(player, "rookie_year"));

                JsonNode draft = player.path("draft");
                p.setDraft_round(parseIntFromJson(draft, "round"));
                p.setDraft_pick(parseIntFromJson(draft, "pick"));

                playerRepo.addPlayer(p);
                playersList.add(p);
            }

            return playersList;
        } catch (Exception e) {
            System.err.println("Error saving player: " + e.getMessage());

            return null;
        }
    }

    public List<Player> fetchAllPlayers() throws InterruptedException {
        List<String> teams = Arrays.asList("5d70a9af-8c2b-4aec-9e68-9acc6ddb93e4",
                "3c409388-ab73-4c7f-953d-3a71062240f6",
                "a015b02d-845c-40c1-8ef4-844984f47e4d",
                "5f0b5caf-708b-4300-92f2-53b51d83ec06",
                "4f57ec40-0d35-4b59-bea0-9d040f0d2292",
                "f073a15f-0486-4179-b0a3-dfd0294eb595",
                "0a5ad38d-2fe3-43ba-894b-1ba3d5042ea9",
                "171b097d-01db-4ae8-9d56-035689402ec6",
                "6f017f37-be96-4bdc-b6d3-0a0429c72e89",
                "08ed8274-e29f-4248-bc2e-83cc8ed18d75",
                "0699edf3-5993-4182-b9b4-ec935cbd4fcc",
                "d6a012ed-84aa-48d3-8265-2d3f3ff2199a",
                "5c0d47fe-8539-47b0-9f36-d0b3609ca89b");

        List<Player> allPlayers = new ArrayList<>();

        for (String teamID : teams) {
            allPlayers.addAll(fetchPlayers(teamID));
            Thread.sleep(2000);
        }

        return allPlayers;
    }

    private String getStringFromJson(JsonNode parentNode, String fieldName) {
        JsonNode fieldNode = parentNode.get(fieldName);
        return (fieldNode != null && !fieldNode.isNull()) ? fieldNode.asText() : "";
    }

    private Integer getIntFromJson(JsonNode parentNode, String fieldName) {
        JsonNode fieldNode = parentNode.get(fieldName);
        return (fieldNode != null && !fieldNode.isNull()) ? fieldNode.asInt() : 0;
    }

    private Integer parseIntFromJson(JsonNode parentNode, String fieldName) {
        JsonNode fieldNode = parentNode.get(fieldName);
        if (fieldNode != null && !fieldNode.isNull()) {
            try {
                return Integer.parseInt(fieldNode.asText());
            } catch (NumberFormatException e) {
                System.err.println("Could not parse " + fieldName + ": " + fieldNode.asText());
                return 0;
            }
        }
        return 0;
    }
}
