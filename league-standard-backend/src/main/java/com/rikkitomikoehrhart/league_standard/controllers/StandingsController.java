package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.StandingsRepo;
import com.rikkitomikoehrhart.league_standard.model.TeamStanding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/standings")
public class StandingsController {

    @Autowired
    private StandingsRepo standingsRepo;

    @GetMapping
    public List<TeamStanding> getStandings() {
        return standingsRepo.getLeagueStandings();
    }

    @GetMapping("/{rank}")
    public TeamStanding getStandingByRank(@PathVariable int rank) {
        return standingsRepo.getTeamStandingByLeagueRank(rank);
    }
}
