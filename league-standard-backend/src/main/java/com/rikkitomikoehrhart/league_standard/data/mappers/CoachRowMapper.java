package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.Coach;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachRowMapper implements RowMapper<Coach> {
    private TeamRowMapper teamRowMapper = new TeamRowMapper();

    @Override
    public Coach mapRow(ResultSet rs, int rowNum) throws SQLException {
        Coach coach = new Coach();

        coach.setId(rs.getString("coach_id"));
        coach.setTeam(teamRowMapper.mapRow(rs, rowNum));
        coach.setFirstName(rs.getString("first_name"));
        coach.setLastName(rs.getString("last_name"));
        coach.setPosition(rs.getString("position"));

        return coach;
    }
}
