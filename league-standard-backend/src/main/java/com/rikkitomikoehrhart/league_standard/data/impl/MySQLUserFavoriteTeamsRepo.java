package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.UserFavoriteTeamsRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.UserFavoriteTeamsMapper;
import com.rikkitomikoehrhart.league_standard.model.UserFavoriteTeams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLUserFavoriteTeamsRepo implements UserFavoriteTeamsRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserFavoriteTeamsMapper mapper = new UserFavoriteTeamsMapper();

    @Override
    public UserFavoriteTeams getUserFavoriteTeamsByID(String id) {
        String sql = """
        SELECT f.id as user_favorite_teams_id,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM user_favorite_teams as f
        JOIN teams t ON f.team_id = t.id
        WHERE f.id = ?;
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.userFavoriteTeamsRowMapper(), id);
        } catch (Exception e) {
            System.err.println("User Favorite Team not found with ID: " + id);
            return null;
        }
    }

    @Override
    public List<UserFavoriteTeams> getAllUserFavoriteTeams() {
        String sql = """
        SELECT f.id as user_favorite_teams_id,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM user_favorite_teams as f
        JOIN teams t ON f.team_id = t.id;
        """;

        return jdbcTemplate.query(sql, mapper.userFavoriteTeamsRowMapper());
    }

    @Override
    public UserFavoriteTeams addUserFavoriteTeam(UserFavoriteTeams userFavoriteTeams) {
        String sql = """
        INSERT INTO user_favorite_teams (team_id)
        VALUES (?)
        """;

        try {
            jdbcTemplate.update(sql, userFavoriteTeams.getTeamID());
        } catch (Exception e) {
            System.err.println("Error adding user favorite team: " + e.getMessage());
        }

        return userFavoriteTeams;
    }

    @Override
    public void updateUserFavoriteTeams(UserFavoriteTeams userFavoriteTeams) {
        String sql = """
        UPDATE user_favorite_teams
        SET team_id = ?,
        WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql, userFavoriteTeams.getTeamID());
        } catch (Exception e) {
            System.err.println("Error updating user favorite team: " + e.getMessage());
        }
    }

    @Override
    public void deleteUserFavoriteTeams(String id) {
        String sql = """
        DELETE FROM user_favorite_teams WHERE team_id = ?
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting user favorite team: " + e.getMessage());
        }

    }
}
