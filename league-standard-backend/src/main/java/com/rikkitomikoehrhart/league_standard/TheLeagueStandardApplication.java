package com.rikkitomikoehrhart.league_standard;

import com.rikkitomikoehrhart.league_standard.model.Game;
import com.rikkitomikoehrhart.league_standard.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TheLeagueStandardApplication implements CommandLineRunner {
	@Autowired
	private APIService apiService;

	public static void main(String[] args) {
		SpringApplication.run(TheLeagueStandardApplication.class, args);
	}

	@Override
	public void run(String... args) throws InterruptedException {
		System.out.println("Testing Sportradar API...");
		System.out.println("API Key: " + apiService.getWnbaAPIKey());
		System.out.println("Base URL: " + apiService.getWnbaBaseUrl());

		List<Game> games = apiService.fetchAllWNBAGames(2025);

		System.out.println("Found " + games.size() + " games: ");

		for (Game game : games) {
			System.out.println(game.getId() + " - " + game.getScheduled());
		}
	}
}
