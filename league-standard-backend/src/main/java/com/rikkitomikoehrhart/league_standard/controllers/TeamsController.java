package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.TeamRepo;
import com.rikkitomikoehrhart.league_standard.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    @Autowired
    private TeamRepo teamRepo;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepo.getAllTeams();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable String id) {
        return teamRepo.getTeamByID(id);
    }
}
