package com.rikkitomikoehrhart.league_standard;

import com.rikkitomikoehrhart.league_standard.data.CoachRepo;
import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.data.TeamColorsRepo;
import com.rikkitomikoehrhart.league_standard.model.Game;
import com.rikkitomikoehrhart.league_standard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TheLeagueStandardApplication implements CommandLineRunner {
	@Autowired
	private TeamProfileService teamProfileService;

    @Autowired
    private GameDataService gameDataService;

    @Autowired
    private StandingsDataService standingsDataService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private NewsDataService newsDataService;

	public static void main(String[] args) {
		SpringApplication.run(TheLeagueStandardApplication.class, args);
	}

	@Override
	public void run(String... args) throws InterruptedException {
		System.out.println("System Running...");


        // UPDATE GAMES
        // gameDataService.loadGamesFromAPI(2025);

        // UPDATE STANDINGS
        // standingsDataService.fetchStandings(2025, "REG");

        // ADD PLAYERS
        // playerService.fetchAllPlayers();

        // ADD/UPDATE NEWS
        newsDataService.loadNewsData();
	}
}
