package com.rikkitomikoehrhart.league_standard.service;

import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameDataService {
    @Autowired
    private APIService apiService;

    @Autowired
    private GameRepo gameRepo;

    public void loadGamesFromAPI(int year) throws InterruptedException {
        List<Game> gamesFromAPI = apiService.fetchAllWNBAGames(year);

        for (Game game : gamesFromAPI) {
            gameRepo.addGame(game);
        }

        System.out.println("Saved " + gamesFromAPI.size() + " games to database");
    }
}
