package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.Team;
import com.rikkitomikoehrhart.league_standard.model.TeamStanding;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;

public class StandingsRowMapper {
    public RowMapper<TeamStanding> teamStandingRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            TeamStanding standing = new TeamStanding();

            standing.setId(rs.getString("id"));

            Team team = new Team();
            team.setId(rs.getString("team_id"));
            team.setAlias(rs.getString("alias"));
            team.setMarket(rs.getString("market"));
            team.setName(rs.getString("name"));
            team.setYearFounded(rs.getInt("year_founded"));
            team.setMascot(rs.getString("mascot"));
            team.setOwner(rs.getString("owner"));

            standing.setTeam(team);
            standing.setWins(rs.getInt("wins"));
            standing.setLoses(rs.getInt("losses"));
            standing.setWin_pct(rs.getFloat("win_pct"));
            standing.setPoints_for(rs.getFloat("points_for"));
            standing.setPoints_against(rs.getFloat("points_against"));
            standing.setPoint_diff(rs.getFloat("point_diff"));
            standing.setLeague_rank(rs.getInt("league_rank"));

            return standing;
        };
    }
}
