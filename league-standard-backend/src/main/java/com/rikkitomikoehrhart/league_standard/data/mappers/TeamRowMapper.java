package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.Team;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class TeamRowMapper {

    public RowMapper<Team> teamRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            Team team = new Team();

            team.setId(rs.getString("team_id"));
            team.setAlias(rs.getString("alias"));
            team.setMarket(rs.getString("market"));
            team.setName(rs.getString("name"));
            team.setYearFounded(rs.getInt("year_founded"));
            team.setMascot(rs.getString("mascot"));
            team.setOwner(rs.getString("owner"));

            return team;
        };
    }
}
