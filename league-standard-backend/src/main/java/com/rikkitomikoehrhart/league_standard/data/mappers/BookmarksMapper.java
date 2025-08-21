package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.Bookmark;
import com.rikkitomikoehrhart.league_standard.model.Game;
import com.rikkitomikoehrhart.league_standard.model.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class BookmarksMapper {

    public RowMapper<Bookmark> bookmarkRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            Bookmark bookmark = new Bookmark();

            bookmark.setId(rs.getString("id"));

            if (rs.getString("news_id") == null) {
                bookmark.setNews(null);
            } else {
                News news = new News();
                news.setId(rs.getString("news_id"));
                bookmark.setNews(news);
            }

            if (rs.getString("game_id") == null) {
                bookmark.setGame(null);
            } else {
                Game game = new Game();
                game.setId(rs.getString("game_id"));
                bookmark.setGame(game);
            }

            return bookmark;
        };
    }
}
