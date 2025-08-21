package com.rikkitomikoehrhart.league_standard.model;

public class Bookmark {
    private String id;
    private News news;
    private Game game;

    public Bookmark() {};
    public Bookmark(String id, News news, Game game) {
        this.id = id;
        this.news = news;
        this.game = game;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
