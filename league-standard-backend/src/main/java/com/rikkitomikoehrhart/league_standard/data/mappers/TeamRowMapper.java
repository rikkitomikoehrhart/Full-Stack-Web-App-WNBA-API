package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.Team;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamRowMapper implements RowMapper<Team> {

    @Override
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
        Team team = new Team();

    }
}
