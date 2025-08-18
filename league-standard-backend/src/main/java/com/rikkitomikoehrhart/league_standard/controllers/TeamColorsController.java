package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.TeamColorsRepo;
import com.rikkitomikoehrhart.league_standard.model.TeamColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/colors")
public class TeamColorsController {

    @Autowired
    private TeamColorsRepo teamColorsRepo;

    @GetMapping
    public List<TeamColors> getAllTeamColors() { return teamColorsRepo.getAllTeamColors(); };
}
