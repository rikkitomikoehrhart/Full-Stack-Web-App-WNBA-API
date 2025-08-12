package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.TeamRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.TeamRowMapper;
import com.rikkitomikoehrhart.league_standard.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLTeamRepo implements TeamRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private TeamRowMapper mapper = new TeamRowMapper();

    @Override
    public Team getTeamByID(String id) {
        String sql = """
        SELECT id as team_id, alias, market, name, year_founded, mascot, owner
        FROM teams
        WHERE id = ?;
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.teamRowMapper(), id);
        } catch (Exception e) {
            System.err.println("Team not found with id: " + id);
            return null;
        }
    }

    @Override
    public List<Team> getAllTeams() {
        String sql = """
        SELECT id as team_id, alias, market, name, year_founded, mascot, owner
        FROM teams;
        """;
        return jdbcTemplate.query(sql, mapper.teamRowMapper());
    }

    @Override
    public Team addTeam(Team team) {
        String sql = """
        INSERT INTO teams (id, alias, market, name, year_founded, mascot, owner)
        VALUES (?, ?, ?, ?, ?, ?, ?);
        """;

        try {
            jdbcTemplate.update(sql,
                    team.getId(),
                    team.getAlias(),
                    team.getMarket(),
                    team.getName(),
                    team.getYearFounded(),
                    team.getMascot(),
                    team.getOwner());
        } catch (Exception e) {
            System.err.println("Error adding team: " + e.getMessage());
        }

        return team;
    }

    @Override
    public void updateTeam(Team team) {
        String sql = """
        UPDATE teams
        SET alias = ?, market = ?, name = ?, year_founded = ?, mascot = ?, owner = ?
        WHERE id = ?;
        """;

        try {
            jdbcTemplate.update(sql,
                    team.getAlias(),
                    team.getMarket(),
                    team.getName(),
                    team.getYearFounded(),
                    team.getMascot(),
                    team.getOwner(),
                    team.getId());
        } catch (Exception e) {
            System.err.println("Error updating team: " + e.getMessage());
        }
    }

    @Override
    public void deleteTeam(String id) {
        String sql = """
        DELETE FROM teams WHERE id = ?;
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting team: " + e.getMessage());
        }
    }
}
