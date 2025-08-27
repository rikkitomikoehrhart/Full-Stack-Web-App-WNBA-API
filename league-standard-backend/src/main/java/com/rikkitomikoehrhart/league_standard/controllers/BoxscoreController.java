package com.rikkitomikoehrhart.league_standard.controllers;


import com.rikkitomikoehrhart.league_standard.data.GameBoxscoreRepo;
import com.rikkitomikoehrhart.league_standard.model.GameBoxscore;
import com.rikkitomikoehrhart.league_standard.service.BoxscoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/boxscores")
public class BoxscoreController {

    @Autowired
    private GameBoxscoreRepo gameBoxscoreRepo;

    @Autowired
    private BoxscoreDataService boxscoreDataService;

    @GetMapping
    public List<GameBoxscore> getAllBoxscores() {
        return gameBoxscoreRepo.getAllBoxscores();
    }

    @GetMapping("/{id}")
    public GameBoxscore getBoxscoreByID(@PathVariable int id) {
        return gameBoxscoreRepo.getGameBoxscoreByID(id);
    }


    @GetMapping("/game/{gameID}")
    public List<GameBoxscore> getBoxscoresByGame(@PathVariable String gameID) {
        return gameBoxscoreRepo.getBoxscoresByGameID(gameID);
    }

    @GetMapping("/game/{gameID}/team/{teamID}")
    public GameBoxscore getBoxscoreByGameAndTeam(@PathVariable String gameID, @PathVariable String teamID) {
        return gameBoxscoreRepo.getBoxscoreByGameAndTeam(gameID, teamID);
    }

    @PostMapping("/load/{gameID}")
    public ResponseEntity<String> loadBoxscoreData(@PathVariable String gameID) {
        try {
            boxscoreDataService.loadBoxscoreData(gameID);
            return ResponseEntity.ok("Boxscore data loaded for game: " + gameID);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error loading boxscore date for game " + gameID + ": " + e.getMessage());
        }
    }
}
