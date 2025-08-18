package com.rikkitomikoehrhart.league_standard.model;

public class UserFavoritePlayer {
    private String id;
    private Player player;

    public UserFavoritePlayer() {};
    public UserFavoritePlayer(String id, Player player) {
        this.id = id;
        this.player = player;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getPlayerId() {
        return player.getId();
    }
}
