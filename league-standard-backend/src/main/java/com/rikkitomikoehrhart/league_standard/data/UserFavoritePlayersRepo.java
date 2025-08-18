package com.rikkitomikoehrhart.league_standard.data;


import com.rikkitomikoehrhart.league_standard.model.UserFavoritePlayer;

import java.util.List;

public interface UserFavoritePlayersRepo {
    public UserFavoritePlayer getUserFavoritePlayersByID(String id);
    public List<UserFavoritePlayer> getAllUserFavoritePlayers();
    public UserFavoritePlayer addUserFavoritePlayer(UserFavoritePlayer userFavoritePlayer);
    public void updateUserFavoritePlayer(UserFavoritePlayer userFavoritePlayer);
    public void deleteUserFavoritePlayer(String id);

}
