package com.rikkitomikoehrhart.league_standard.data;

import com.rikkitomikoehrhart.league_standard.model.TeamColors;

import java.util.List;

public interface TeamColorsRepo {
    public TeamColors getTeamColorsByID(String id);
    public List<TeamColors> getAllTeamColors();
    public TeamColors addTeamColors(TeamColors teamColors);
    public void updateTeamColors(TeamColors teamColors);
    public void deleteTeamColors(String id);

    interface StandingsRepo {
    }
}
