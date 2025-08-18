package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.UserFavoritePlayersRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.UserFavoritePlayersMapper;
import com.rikkitomikoehrhart.league_standard.model.UserFavoritePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLUserFavoritePlayersRepo implements UserFavoritePlayersRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserFavoritePlayersMapper mapper = new UserFavoritePlayersMapper();

    @Override
    public UserFavoritePlayer getUserFavoritePlayersByID(String id) {
        String sql = """
        SELECT f.id AS id,
               p.id  AS player_id, p.status, p.first_name, p.last_name, p.height, p.position, p.jersey_number, p.experience, p.college, p.birth_place, p.birthdate, p.rookie_year, p.draft_round, p.draft_pick,
               t.id AS team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM user_favorite_players f
        JOIN players p ON f.player_id = p.id
        JOIN teams t ON p.team_id = t.id
        WHERE id = ?;
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.userFavoritePlayersRowMapper(), id);
        } catch (Exception e) {
            System.err.println("User Favorite Player not found with id: " + id);
            return null;
        }

    }



    @Override
    public List<UserFavoritePlayer> getAllUserFavoritePlayers() {
        String sql = """
        SELECT f.id AS id,
               p.id  AS player_id, p.status, p.first_name, p.last_name, p.height, p.position, p.jersey_number, p.experience, p.college, p.birth_place, p.birthdate, p.rookie_year, p.draft_round, p.draft_pick,
               t.id AS team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM user_favorite_players f
        JOIN players p ON f.player_id = p.id
        JOIN teams t ON p.team_id = t.id
        """;

        return jdbcTemplate.query(sql, mapper.userFavoritePlayersRowMapper());
    }

    @Override
    public UserFavoritePlayer addUserFavoritePlayer(UserFavoritePlayer userFavoritePlayer) {
        String sql = """
        INSERT INTO user_favorite_players (player_id)
        VALUES (?)
        """;

        try {
            jdbcTemplate.update(sql, userFavoritePlayer.getPlayerId());
        } catch (Exception e) {
            System.err.println("Error adding user favorite player: " + e.getMessage());
        }

        return userFavoritePlayer;
    }

    @Override
    public void updateUserFavoritePlayer(UserFavoritePlayer userFavoritePlayer) {
        String sql = """
        UPDATE user_favorite_players
        SET player_id = ?,
        WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql, userFavoritePlayer.getPlayerId(), userFavoritePlayer.getId());
        } catch (Exception e) {
            System.err.println("Error updating user favorite team: " + e.getMessage());
        }
    }



    @Override
    public void deleteUserFavoritePlayer(String id) {
        String sql = """
        DELETE FROM user_favorite_players WHERE player_id = ?;
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting user favorite player: " + e.getMessage());
        }
    }
}
