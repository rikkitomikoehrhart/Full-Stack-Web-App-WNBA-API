package com.rikkitomikoehrhart.league_standard.data;

import com.rikkitomikoehrhart.league_standard.model.Team;

import java.util.List;

public interface TeamRepo {
    public Team getTeamByID(String id);
    public List<Team> getAllTeams();
    public Team addTeam(Team team);
    public void updateTeam(Team team);
    public Team deleteTeam(String id);
}
