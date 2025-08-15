package com.rikkitomikoehrhart.league_standard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikkitomikoehrhart.league_standard.data.StandingsRepo;
import com.rikkitomikoehrhart.league_standard.model.Team;
import com.rikkitomikoehrhart.league_standard.model.TeamStanding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class StandingsDataService {
    @Value("${wnba.api.key}")
    private String wnbaAPIKey;

    @Value("${wnba.api.base.url}")
    private String wnbaBaseURL;

    @Autowired
    private StandingsRepo standingsRepo;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public StandingsDataService() {
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
        this.objectMapper = new ObjectMapper();
    }

    public void fetchStandings(int year, String seasonType) {
        try {
            String url = String.format("%sseasons/%s/%s/standings.json", wnbaBaseURL, year, seasonType);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(30))
                    .header("accept", "application/json")
                    .header("x-api-key", wnbaAPIKey)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                parseStandingsFromJSON(response.body());
            } else {
                System.err.println("API request failed with status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.err.println("Error fetching standings from API: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void parseStandingsFromJSON(String jsonResponse) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode conferencesNode = root.get("conferences");

            for (JsonNode conference : conferencesNode) {
                JsonNode teamsNode = conference.get("teams");

                for (JsonNode teamNode : teamsNode) {
                    TeamStanding standing = new TeamStanding();
                    Team team = new Team();
                    team.setId(teamNode.get("id").asText());
                    standing.setTeam(team);
                    standing.setWins(teamNode.get("wins").asInt());
                    standing.setLosses(teamNode.get("losses").asInt());
                    standing.setWin_pct((float) teamNode.get("win_pct").asDouble());
                    standing.setPoints_for((float) teamNode.get("points_for").asDouble());
                    standing.setPoints_against((float) teamNode.get("points_against").asDouble());
                    standing.setPoint_diff((float) teamNode.get("point_diff").asDouble());

                    JsonNode cal_rankNode = teamNode.get("calc_rank");
                    standing.setLeague_rank(cal_rankNode.get("league_rank").asInt());

                    standingsRepo.addTeamStanding(standing);
                }

            }

        } catch (Exception e) {
            System.err.print("Error processing standing: " + e.getMessage());
        }
    }

}
