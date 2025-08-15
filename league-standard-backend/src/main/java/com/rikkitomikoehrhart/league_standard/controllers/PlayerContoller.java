package com.rikkitomikoehrhart.league_standard.controllers;


import com.rikkitomikoehrhart.league_standard.data.PlayerRepo;
import com.rikkitomikoehrhart.league_standard.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/players")
public class PlayerContoller {
    @Autowired
    private PlayerRepo playerRepo;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepo.getAllPlayers();
    }

    @GetMapping("/{playerID}")
    public Player getPlayerByID(@PathVariable String id) {
        return playerRepo.getPlayerByID(id);
    }
}
