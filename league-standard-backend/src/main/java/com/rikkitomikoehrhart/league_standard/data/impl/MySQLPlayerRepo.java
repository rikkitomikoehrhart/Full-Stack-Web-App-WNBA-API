package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.PlayerRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.PlayerRowMapper;
import com.rikkitomikoehrhart.league_standard.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLPlayerRepo implements PlayerRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PlayerRowMapper mapper = new PlayerRowMapper();


    @Override
    public Player getPlayerByID(String id) {
        String sql = """
        SELECT p.id, p.status, p.first_name, p.last_name, p.height, p.position, p.jersey_number, p.experience, p.college, p.birth_place, p.birthdate, p.rookie_year, p.draft_round, p.draft_pick,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM players p
        JOIN teams t ON p.team_id = t.id
        WHERE p.id = ?;
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.playerRowMapper(), id);
        } catch (Exception e) {
            System.err.println("Player not found with ID: " + id);
            return null;
        }
    }

    @Override
    public List<Player> getAllPlayers() {
        String sql = """
        SELECT p.id, p.status, p.first_name, p.last_name, p.height, p.position, p.jersey_number, p.experience, p.college, p.birth_place, p.birthdate, p.rookie_year, p.draft_round, p.draft_pick,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM players p
        JOIN teams t ON p.team_id = t.id;
        """;

        return jdbcTemplate.query(sql, mapper.playerRowMapper());
    }

    @Override
    public List<Player> getPlayersByTeam(String id) {
        String sql = """
        SELECT p.id, p.status, p.first_name, p.last_name, p.height, p.position, p.jersey_number, p.experience, p.college, p.birth_place, p.birthdate, p.rookie_year, p.draft_round, p.draft_pick,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM players p
        JOIN teams t ON p.team_id = t.id
        WHERE team_id = ?;
        """;

        return jdbcTemplate.query(sql, mapper.playerRowMapper(), id);
    }

    @Override
    public Player addPlayer(Player player) {
        String sql = """
        INSERT INTO players (id, team_id, status, first_name, last_name, height, position, jersey_number, experience, college, birth_place, birthdate, rookie_year, draft_round, draft_pick)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE
            team_id = VALUES(team_id),
            status = VALUES(status),
            first_name = VALUES(first_name),
            last_name = VALUES(last_name),
            height = VALUES(height),
            position = VALUES(position),
            jersey_number = VALUES(jersey_number),
            experience = VALUES(experience),
            college = VALUES(college),
            birth_place = VALUES(birth_place),
            birthdate = VALUES(birthdate),
            rookie_year = VALUES(rookie_year),
            draft_round = VALUES(draft_round),
            draft_pick = VALUES(draft_pick),
            updated_at = CURRENT_TIMESTAMP
        """;

        try {
            jdbcTemplate.update(sql,
                    player.getId(),
                    player.getTeamID(),
                    player.getStatus(),
                    player.getFirst_name(),
                    player.getLast_name(),
                    player.getHeight(),
                    player.getPosition(),
                    player.getJersey_number(),
                    player.getExperience(),
                    player.getCollege(),
                    player.getBirth_place(),
                    player.getBirthdate(),
                    player.getRookie_year(),
                    player.getDraft_round(),
                    player.getDraft_pick());
        } catch (Exception e) {
            System.err.println("Error adding player: " + e.getMessage());
        }

        return player;
    }

    @Override
    public void updatePlayer(Player player) {
        String sql = """
        UPDATE player
        SET team_id = ?, status = ?, first_name = ?, last_name = ?, height = ?, position = ?, jersey_number = ?, 
            experience = ?, college = ?, brith_place = ?, birthdate = ?, rookie_year = ?, draft_round = ?, draft_pick = ? 
        WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql,
                    player.getTeamID(),
                    player.getStatus(),
                    player.getFirst_name(),
                    player.getLast_name(),
                    player.getHeight(),
                    player.getPosition(),
                    player.getJersey_number(),
                    player.getExperience(),
                    player.getCollege(),
                    player.getBirth_place(),
                    player.getBirthdate(),
                    player.getRookie_year(),
                    player.getDraft_round(),
                    player.getDraft_pick(),
                    player.getId());
        } catch (Exception e) {
            System.err.println("Error updating player: " + e.getMessage());
        }
    }

    @Override
    public void deletePlayer(String id) {
        String sql = """
        DELETE FROM players WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting coach: " + e.getMessage());
        }
    }
}
