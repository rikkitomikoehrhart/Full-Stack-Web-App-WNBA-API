package com.rikkitomikoehrhart.league_standard.data;

import com.rikkitomikoehrhart.league_standard.model.Coach;

import java.util.List;

public interface CoachRepo {
    public Coach getCoachByID(String id);
    public List<Coach> getAllCoaches();
    public Coach addCoach(Coach coach);
    public void updateCoach(Coach coach);
    public Coach deleteCoach(String id);
}
