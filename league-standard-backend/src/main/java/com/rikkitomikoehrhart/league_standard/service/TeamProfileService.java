package com.rikkitomikoehrhart.league_standard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikkitomikoehrhart.league_standard.data.CoachRepo;
import com.rikkitomikoehrhart.league_standard.data.TeamColorsRepo;
import com.rikkitomikoehrhart.league_standard.model.Coach;
import com.rikkitomikoehrhart.league_standard.model.Team;
import com.rikkitomikoehrhart.league_standard.model.TeamColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class TeamProfileService {
    @Value("${wnba.api.key}")
    private String wnbaAPIKey;

    @Value("${wnba.api.base.url}")
    private String wnbaBaseUrl;

    @Autowired
    private CoachRepo coachRepo;

    @Autowired
    private TeamColorsRepo teamColorsRepo;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public TeamProfileService() {
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
        this.objectMapper = new ObjectMapper();
    }

    public void loadTeamProfileData(String teamID) {
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
                processTeamProfileData(response.body(), teamID);
            } else {
                System.err.println("API request failed with status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.err.println("Error fetching team profile for " + teamID + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processTeamProfileData(String jsonResponse, String teamID) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);

            JsonNode coachesNode = root.get("coaches");
            if (coachesNode != null && coachesNode.isArray()) {
                for (JsonNode coachNode : coachesNode) {
                    Coach coach = mapCoachFromJson(coachNode, teamID);
                    if (coach != null) {
                        try {
                            coachRepo.addCoach(coach);
                        } catch (Exception e) {
                            System.err.println("Error saving coach: " + e.getMessage());
                        }
                    }
                }
            }

            JsonNode colorsNode = root.get("team_colors");
            if (colorsNode != null && colorsNode.isArray()) {
                for (JsonNode colorNode : colorsNode) {
                    TeamColors teamColor = mapTeamColorFromJson(colorNode, teamID);
                    if (teamColor != null) {
                        try {
                            teamColorsRepo.addTeamColors(teamColor);
                        } catch (Exception e) {
                            System.err.println("Error saving team color: " + e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error processing team profile data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Coach mapCoachFromJson(JsonNode coachNode, String teamID) {
        try {
            Coach coach = new Coach();

            coach.setId(coachNode.get("id").asText());
            coach.setFirstName(coachNode.get("first_name").asText());
            coach.setLastName(coachNode.get("last_name").asText());
            coach.setPosition(coachNode.get("position").asText());

            Team team = new Team();
            team.setId(teamID);
            coach.setTeam(team);

            return coach;
        } catch (Exception e) {
            System.err.println("Error mapping coach JSON: " + e.getMessage());
            return null;
        }
    }

    private TeamColors mapTeamColorFromJson(JsonNode colorNode, String teamID) {
        try {
            TeamColors teamColor = new TeamColors();

            teamColor.setColorType(colorNode.get("type").asText());
            teamColor.setHexCode(colorNode.get("hex_color").asText());

            Team team = new Team();
            team.setId(teamID);
            teamColor.setTeam(team);

            return teamColor;
        } catch (Exception e) {
            System.err.println("Error mapping team color JSON: " + e.getMessage());
            return null;
        }
    }


}
