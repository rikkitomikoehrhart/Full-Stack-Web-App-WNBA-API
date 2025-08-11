package com.rikkitomikoehrhart.league_standard.data;

import com.rikkitomikoehrhart.league_standard.model.UserFavoriteTeams;

import java.util.List;

public interface UserFavoriteTeamsRepo {
    public UserFavoriteTeams getUserFavoriteTeamsByID(String id);
    public List<UserFavoriteTeams> getAllUserFavoriteTeams();
    public UserFavoriteTeams addUserFavoriteTeam(UserFavoriteTeams userFavoriteTeams);
    public void updateUserFavoriteTeams(UserFavoriteTeams userFavoriteTeams);
    public UserFavoriteTeams deleteUserFavoriteTeams(String id);
}
