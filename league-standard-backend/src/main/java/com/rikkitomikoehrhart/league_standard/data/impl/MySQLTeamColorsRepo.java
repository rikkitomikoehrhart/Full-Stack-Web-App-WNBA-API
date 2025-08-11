package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.TeamColorsRepo;
import com.rikkitomikoehrhart.league_standard.model.TeamColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLTeamColorsRepo implements TeamColorsRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TeamColors getTeamColorsByID(String id) {
        return null;
    }

    @Override
    public List<TeamColors> getAllTeamColors() {
        return List.of();
    }

    @Override
    public TeamColors addTeamColors(TeamColors teamColors) {
        return null;
    }

    @Override
    public void updateTeamColors(TeamColors teamColors) {

    }

    @Override
    public TeamColors deleteTeamColors(String id) {
        return null;
    }
}
