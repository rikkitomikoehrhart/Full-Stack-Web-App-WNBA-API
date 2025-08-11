package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLGameRepo implements GameRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Game getGameByID(int id) {
        return null;
    }

    @Override
    public List<Game> getAllGames() {
        return List.of();
    }

    @Override
    public Game addGame(Game game) {
        return null;
    }

    @Override
    public void updateGame(Game game) {

    }

    @Override
    public Game deleteGame(int id) {
        return null;
    }
}
