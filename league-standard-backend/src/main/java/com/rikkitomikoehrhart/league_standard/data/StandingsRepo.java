package com.rikkitomikoehrhart.league_standard.data;

import com.rikkitomikoehrhart.league_standard.model.TeamStanding;

import java.util.List;

public interface StandingsRepo {
    public TeamStanding getTeamStandingByLeagueRank(int rank);
    public List<TeamStanding> getLeagueStandings();
    public TeamStanding addTeamStanding(TeamStanding standing);
    public void updateTeamStanding(TeamStanding standing);
    public void deleteTeamStanding(int rank);
}
