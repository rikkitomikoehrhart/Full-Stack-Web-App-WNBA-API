package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GamesController {

    @Autowired
    private GameRepo gameRepo;

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepo.getAllGames();
    }

    @GetMapping("/{gameID}")
    public Game getGameByID(@PathVariable String gameID) {
        return gameRepo.getGameByID(gameID);
    }
}
