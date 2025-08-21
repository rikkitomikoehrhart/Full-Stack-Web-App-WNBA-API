package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.data.NewsRepo;
import com.rikkitomikoehrhart.league_standard.model.Bookmark;
import com.rikkitomikoehrhart.league_standard.model.Game;
import com.rikkitomikoehrhart.league_standard.model.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;


public class BookmarksMapper {



    public RowMapper<Bookmark> bookmarkRowMapper(NewsRepo newsRepo, GameRepo gameRepo) {
        return (ResultSet rs, int rowNum) -> {
            Bookmark bookmark = new Bookmark();

            bookmark.setId(rs.getString("id"));


            String newsID = rs.getString("news_id");
            if (newsID == null || newsID.trim().isEmpty()) {
                bookmark.setNews(null);
            } else {
                News news = newsRepo.getNewsByID(newsID);
                bookmark.setNews(news);
            }

            String gameID = rs.getString("game_id");
            if (gameID == null || gameID.trim().isEmpty()) {
                bookmark.setGame(null);
            } else {
                Game game = gameRepo.getGameByID(gameID);
                bookmark.setGame(game);
            }

            return bookmark;
        };
    }
}
