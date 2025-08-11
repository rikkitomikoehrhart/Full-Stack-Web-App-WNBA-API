package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.UserFavoriteTeamsRepo;
import com.rikkitomikoehrhart.league_standard.model.UserFavoriteTeams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLUserFavoriteTeamsRepo implements UserFavoriteTeamsRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserFavoriteTeams getUserFavoriteTeamsByID(int id) {
        return null;
    }

    @Override
    public List<UserFavoriteTeams> getAllUserFavoriteTeams() {
        return List.of();
    }

    @Override
    public UserFavoriteTeams addUserFavoriteTeam(UserFavoriteTeams userFavoriteTeams) {
        return null;
    }

    @Override
    public void updateUserFavoriteTeams(UserFavoriteTeams userFavoriteTeams) {

    }

    @Override
    public UserFavoriteTeams deleteUserFavoriteTeams(int id) {
        return null;
    }
}
