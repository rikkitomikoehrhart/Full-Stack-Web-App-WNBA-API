package com.rikkitomikoehrhart.league_standard;

import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.model.Game;
import com.rikkitomikoehrhart.league_standard.service.APIService;
import com.rikkitomikoehrhart.league_standard.service.GameDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TheLeagueStandardApplication implements CommandLineRunner {
	@Autowired
	private APIService apiService;

	@Autowired
	private GameDataService gameDataService;

	@Autowired
	private GameRepo gameRepo;

	public static void main(String[] args) {
		SpringApplication.run(TheLeagueStandardApplication.class, args);
	}

	@Override
	public void run(String... args) throws InterruptedException {
		System.out.println("Loading games from API to database...");
		gameDataService.loadGamesFromAPI(2025);

	}
}
