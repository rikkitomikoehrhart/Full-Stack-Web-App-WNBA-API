package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.CoachRepo;
import com.rikkitomikoehrhart.league_standard.model.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLCoachRepo implements CoachRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Coach getCoachByID(int id) {
        return null;
    }

    @Override
    public List<Coach> getAllCoaches() {
        return List.of();
    }

    @Override
    public Coach addCoach(Coach coach) {
        return null;
    }

    @Override
    public void updateCoach(Coach coach) {

    }

    @Override
    public Coach deleteCoach(int id) {
        return null;
    }
}
