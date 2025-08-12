package com.rikkitomikoehrhart.league_standard;

import com.rikkitomikoehrhart.league_standard.data.CoachRepo;
import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.data.TeamColorsRepo;
import com.rikkitomikoehrhart.league_standard.model.Game;
import com.rikkitomikoehrhart.league_standard.service.APIService;
import com.rikkitomikoehrhart.league_standard.service.GameDataService;
import com.rikkitomikoehrhart.league_standard.service.TeamProfileService;
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
	private TeamProfileService teamProfileService;

	@Autowired
	private CoachRepo coachRepo;

	@Autowired
	private TeamColorsRepo teamColorsRepo;

	public static void main(String[] args) {
		SpringApplication.run(TheLeagueStandardApplication.class, args);
	}

	@Override
	public void run(String... args) throws InterruptedException {
		System.out.println("System Running...");

	}
}
