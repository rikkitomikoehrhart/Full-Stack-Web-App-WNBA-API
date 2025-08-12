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
		System.out.println("Loading coaches and team colors from API to database...");

		List<String> wnbaTeamIds = List.of(
				"171b097d-01db-4ae8-9d56-035689402ec6", // Las Vegas Aces
				"5d70a9af-8c2b-4aec-9e68-9acc6ddb93e4", // Atlanta Dream
				"f073a15f-0486-4179-b0a3-dfd0294eb595", // Indiana Fever
				"08ed8274-e29f-4248-bc2e-83cc8ed18d75", // New York Liberty
				"6f017f37-be96-4bdc-b6d3-0a0429c72e89", // Minnesota Lynx
				"0699edf3-5993-4182-b9b4-ec935cbd4fcc", // Phoenix Mercury
				"5c0d47fe-8539-47b0-9f36-d0b3609ca89b", // Washington Mystics
				"3c409388-ab73-4c7f-953d-3a71062240f6", // Chicago Sky
				"0a5ad38d-2fe3-43ba-894b-1ba3d5042ea9", // Los Angeles Sparks
				"d6a012ed-84aa-48d3-8265-2d3f3ff2199a", // Seattle Storm
				"a015b02d-845c-40c1-8ef4-844984f47e4d", // Connecticut Sun
				"4f57ec40-0d35-4b59-bea0-9d040f0d2292", // Golden State Valkyries
				"5f0b5caf-708b-4300-92f2-53b51d83ec06"  // Dallas Wings
		);

		for (String id : wnbaTeamIds) {
			teamProfileService.loadTeamProfileData(id);
			Thread.sleep(2000);
		}


	}
}
