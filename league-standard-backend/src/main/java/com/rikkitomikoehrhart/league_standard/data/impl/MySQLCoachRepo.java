package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.CoachRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.CoachRowMapper;
import com.rikkitomikoehrhart.league_standard.model.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLCoachRepo implements CoachRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoachRowMapper mapper = new CoachRowMapper();

    @Override
    public Coach getCoachByID(String id) {
        String sql = """
        SELECT c.id as coach_id, c.first_name, c.last_name, c.position,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM coaches c
        JOIN teams t ON c.team_id = t.id
        WHERE c.id = ?;
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.coachRowMapper(), id);
        } catch (Exception e) {
            System.err.println("Coach not found with ID: " + id);
            return null;
        }
    }

    @Override
    public List<Coach> getAllCoaches() {
        String sql = """
        SELECT c.id as coach_id, c.first_name, c.last_name, c.position,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM coaches c
        JOIN teams t ON c.team_id = t.id
        """;

        return jdbcTemplate.query(sql, mapper.coachRowMapper());
    }

    @Override
    public Coach addCoach(Coach coach) {
        String sql = """
        INSERT INTO coaches (id, team_id, first_name, last_name, position)
        VALUES (?, ?, ?, ?, ?);
        """;

        try {
            jdbcTemplate.update(sql,
                    coach.getId(),
                    coach.getTeamID(),
                    coach.getFirstName(),
                    coach.getLastName(),
                    coach.getPosition());
        } catch (Exception e) {
            System.err.println("Error adding coach: " + e.getMessage());
        }
        return coach;
    }

    @Override
    public void updateCoach(Coach coach) {
        String sql = """
        UPDATE coaches
        SET team_id = ?, first_name = ?, last_name = ?, position = ?
        WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql,
                    coach.getTeamID(),
                    coach.getFirstName(),
                    coach.getLastName(),
                    coach.getPosition(),
                    coach.getId());
        } catch (Exception e) {
            System.err.println("Error updating coach: " + e.getMessage());
        }
    }

    @Override
    public void deleteCoach(String id) {
        String sql = """
        DELETE FROM coaches WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting coach: " + e.getMessage());
        }
    }
}
