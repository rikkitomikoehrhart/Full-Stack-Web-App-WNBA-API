package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.TeamRepo;
import com.rikkitomikoehrhart.league_standard.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLTeamRepo implements TeamRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Team getTeamByID(int id) {
        return null;
    }

    @Override
    public List<Team> getAllTeams() {
        return List.of();
    }

    @Override
    public Team addTeam(Team team) {
        return null;
    }

    @Override
    public void updateTeam(Team team) {

    }

    @Override
    public Team deleteTeam(int id) {
        return null;
    }
}
