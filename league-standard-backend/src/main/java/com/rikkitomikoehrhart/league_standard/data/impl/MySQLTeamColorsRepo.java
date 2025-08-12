package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.TeamColorsRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.TeamColorsRowMapper;
import com.rikkitomikoehrhart.league_standard.model.TeamColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLTeamColorsRepo implements TeamColorsRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private TeamColorsRowMapper mapper = new TeamColorsRowMapper();

    @Override
    public TeamColors getTeamColorsByID(String id) {
        String sql = """
        SELECT c.id as team_colors_id, c.color_type, c.hex_code,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM team_colors c
        JOIN teams t ON c.team_id = t.id
        WHERE id = ?
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.teamColorsRowMapper(), id);
        } catch (Exception e) {
            System.err.println("Team colors not found with ID: " + id);
            return null;
        }
    }

    @Override
    public List<TeamColors> getAllTeamColors() {
        String sql = """
        SELECT c.id as team_colors_id, c.color_type, c.hex_code,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM team_colors c
        JOIN teams t ON c.team_id = t.id;
        """;
        return jdbcTemplate.query(sql, mapper.teamColorsRowMapper());
    }

    @Override
    public TeamColors addTeamColors(TeamColors teamColors) {
        String sql = """
        INSERT INTO team_colors (id, team_id, color_type, hex_code)
        VALUES (?, ?, ?, ?);
        """;

        try {
            jdbcTemplate.update(sql,
                    teamColors.getId(),
                    teamColors.getTeamID(),
                    teamColors.getColorType(),
                    teamColors.getHexCode());
        } catch (Exception e) {
            System.err.println("Error adding team colors: " + e.getMessage());
        }
        return teamColors;
    }

    @Override
    public void updateTeamColors(TeamColors teamColors) {
        String sql = """
        UPDATE team_colors
        SET team_id = ?, color_type = ?, hex_code = ?
        WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql,
                    teamColors.getTeamID(),
                    teamColors.getColorType(),
                    teamColors.getHexCode(),
                    teamColors.getId());
        } catch (Exception e) {
            System.err.println("Error updating team colors: " + e.getMessage());
        }
    }

    @Override
    public void deleteTeamColors(String id) {
        String sql = """
        DELETE FROM team_colors WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting team colors: " + e.getMessage());
        }
    }
}
