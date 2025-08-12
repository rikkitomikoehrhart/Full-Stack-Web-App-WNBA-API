package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.TeamRepo;
import com.rikkitomikoehrhart.league_standard.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
