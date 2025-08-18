package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.UserFavoritePlayersRepo;
import com.rikkitomikoehrhart.league_standard.data.UserFavoriteTeamsRepo;
import com.rikkitomikoehrhart.league_standard.model.Player;
import com.rikkitomikoehrhart.league_standard.model.Team;
import com.rikkitomikoehrhart.league_standard.model.UserFavoritePlayer;
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

    @Autowired
    private UserFavoritePlayersRepo favoritePlayersRepo;


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

    @PostMapping("/players/{playerID}")
    public ResponseEntity<String> addFavoritePlayer(@PathVariable String playerID) {
        UserFavoritePlayer favoritePlayer = new UserFavoritePlayer();
        Player player = new Player();
        Team team = new Team();
        player.setTeam(team);
        player.setId(playerID);
        favoritePlayer.setPlayer(player);

        favoritePlayersRepo.addUserFavoritePlayer(favoritePlayer);
        return ResponseEntity.ok("Player added to favorites");
    }

    @DeleteMapping("/players/{playerID}")
    public ResponseEntity<String> removeFavoritePlayer(@PathVariable String playerID) {
        favoritePlayersRepo.deleteUserFavoritePlayer(playerID);
        return ResponseEntity.ok("Player removed from favorites");
    }

    @GetMapping("/players")
    public List<UserFavoritePlayer> getFavoritePlayers() {
        return favoritePlayersRepo.getAllUserFavoritePlayers();
    }

}
