package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.UserFavoriteTeamsRepo;
import com.rikkitomikoehrhart.league_standard.model.Team;
import com.rikkitomikoehrhart.league_standard.model.UserFavoriteTeams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/favorites")
public class FavoritesController {

    @Autowired
    private UserFavoriteTeamsRepo favoriteTeamsRepo;

    @PostMapping("/teams/{teamID}")
    public ResponseEntity<String> addFavoriteTeam(@PathVariable String teamID) {
        UserFavoriteTeams favorite = new UserFavoriteTeams();
        Team team = new Team();
        team.setId(teamID);
        favorite.setTeam(team);

        favoriteTeamsRepo.addUserFavoriteTeam(favorite);
        return ResponseEntity.ok("Team added to favorites");
    }

    @DeleteMapping("/teams/{teamID}")
    public ResponseEntity<String> removeFavoriteTeam(@PathVariable String teamID) {
        favoriteTeamsRepo.deleteUserFavoriteTeams(teamID);
        return ResponseEntity.ok("Team removed from favorites");
    }

    @GetMapping("/teams")
    public List<UserFavoriteTeams> getFavoriteTeams() {
        return favoriteTeamsRepo.getAllUserFavoriteTeams();
    }


}
