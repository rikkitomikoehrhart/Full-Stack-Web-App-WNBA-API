package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.PlayerSeasonStatsRepo;
import com.rikkitomikoehrhart.league_standard.model.PlayerSeasonStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/season-stats")
public class PlayerSeasonalStatsController {

    @Autowired
    private PlayerSeasonStatsRepo playerSeasonStatsRepo;


    @GetMapping
    public List<PlayerSeasonStats> getAllPlayerSeasonalStats() { return playerSeasonStatsRepo.getAllPlayerSeasonStats(); };

    @GetMapping("/{id}")
    public PlayerSeasonStats getPlayerSeasonStatsByID(@PathVariable String id) {
        return playerSeasonStatsRepo.getPlayerSeasonStatsByID(id);
    }
}
