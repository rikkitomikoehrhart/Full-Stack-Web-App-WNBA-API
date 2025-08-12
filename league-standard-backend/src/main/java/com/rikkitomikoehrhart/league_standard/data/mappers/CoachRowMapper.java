package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.Coach;
import com.rikkitomikoehrhart.league_standard.model.Team;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class CoachRowMapper {
    public RowMapper<Coach> coachRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            Coach coach = new Coach();

            coach.setId(rs.getString("coach_id"));

            Team team = new Team();
            team.setId(rs.getString("team_id"));
            team.setAlias(rs.getString("alias"));
            team.setMarket(rs.getString("market"));
            team.setName(rs.getString("name"));
            team.setYearFounded(rs.getInt("year_founded"));
            team.setMascot(rs.getString("mascot"));
            team.setOwner(rs.getString("owner"));


            coach.setTeam(team);
            coach.setFirstName(rs.getString("first_name"));
            coach.setLastName(rs.getString("last_name"));
            coach.setPosition(rs.getString("position"));

            return coach;
        };
    }
}
