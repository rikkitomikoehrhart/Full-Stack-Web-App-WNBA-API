package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.StandingsRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.StandingsRowMapper;
import com.rikkitomikoehrhart.league_standard.model.TeamStanding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLStandingsRepo implements StandingsRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StandingsRowMapper mapper = new StandingsRowMapper();

    @Override
    public TeamStanding getTeamStandingByLeagueRank(int rank) {
        String sql = """
        SELECT s.id, s.wins, s.losses, s.win_pct, s.points_for, s.points_against, s.point_diff, s.league_rank,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM standings s
        JOIN teams t ON s.team_id = t.id
        WHERE league_rank = ?;
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.teamStandingRowMapper(), rank);
        } catch (Exception e) {
            System.err.println("Team not found at rank: " + rank);
            return null;
        }

    }

    @Override
    public List<TeamStanding> getLeagueStandings() {
        String sql = """
        SELECT s.id, s.wins, s.losses, s.win_pct, s.points_for, s.points_against, s.point_diff, s.league_rank,
               t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
        FROM standings s
        JOIN teams t ON s.team_id = t.id
        """;


        return jdbcTemplate.query(sql, mapper.teamStandingRowMapper());
    }


    @Override
    public TeamStanding addTeamStanding(TeamStanding standing) {
        String sql = """
        INSERT INTO standings (team_id, wins, losses, win_pct, points_for, points_against, point_diff, league_rank)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try {
            jdbcTemplate.update(sql,
                    standing.getTeamID(),
                    standing.getWins(),
                    standing.getLoses(),
                    standing.getWin_pct(),
                    standing.getPoints_for(),
                    standing.getPoints_against(),
                    standing.getPoint_diff(),
                    standing.getLeague_rank());
        } catch (Exception e) {
            System.err.println("Error adding Rank: " + e.getMessage());
        }

        return standing;
    }

    @Override
    public void updateTeamStanding(TeamStanding standing) {
        String sql = """
        UPDATE standings
        SET team_id = ?, wins = ?, losses = ?, win_pct = ?, points_for = ?, points_against = ?, point_diff = ?, league_rank = ?
        WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql,
                    standing.getTeamID(),
                    standing.getWins(),
                    standing.getLoses(),
                    standing.getWin_pct(),
                    standing.getPoints_for(),
                    standing.getPoints_against(),
                    standing.getPoint_diff(),
                    standing.getLeague_rank(),
                    standing.getId());
        } catch (Exception e) {
            System.err.println("Error updating rank: " + e.getMessage());
        }
    }


    @Override
    public void deleteTeamStanding(int rank) {
        String sql = """
        DELETE FROM standings WHERE rank = ?;
        """;

        try {
            jdbcTemplate.update(sql, rank);
        } catch (Exception e) {
            System.err.println("Error deleting standing: " + e.getMessage());
        }
    }
}
