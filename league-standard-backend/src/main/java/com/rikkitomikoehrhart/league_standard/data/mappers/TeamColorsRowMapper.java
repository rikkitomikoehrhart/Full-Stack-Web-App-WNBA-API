package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.Team;
import com.rikkitomikoehrhart.league_standard.model.TeamColors;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class TeamColorsRowMapper {

    public RowMapper<TeamColors> teamColorsRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            TeamColors teamColors = new TeamColors();

            teamColors.setId(rs.getString("team_colors_id"));

            Team team = new Team();
            team.setId(rs.getString("team_id"));
            team.setAlias(rs.getString("alias"));
            team.setMarket(rs.getString("market"));
            team.setName(rs.getString("name"));
            team.setYearFounded(rs.getInt("year_founded"));
            team.setMascot(rs.getString("mascot"));
            team.setOwner(rs.getString("owner"));
            teamColors.setTeam(team);

            teamColors.setColorType(rs.getString("color_type"));
            teamColors.setHexCode(rs.getString("hex_code"));

            return teamColors;
        };

    }
}
