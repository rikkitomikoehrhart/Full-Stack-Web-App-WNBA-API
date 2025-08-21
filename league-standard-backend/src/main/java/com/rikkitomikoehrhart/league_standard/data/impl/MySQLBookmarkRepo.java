package com.rikkitomikoehrhart.league_standard.data.impl;

import com.rikkitomikoehrhart.league_standard.data.BookmarksRepo;
import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.data.NewsRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.BookmarksMapper;
import com.rikkitomikoehrhart.league_standard.model.Bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLBookmarkRepo implements BookmarksRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private GameRepo gameRepo;

    private BookmarksMapper mapper = new BookmarksMapper();

    @Override
    public Bookmark getBookmarkByID(String id) {
        String sql = """
        SELECT * FROM bookmarks
        WHERE id = ?
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.bookmarkRowMapper(newsRepo, gameRepo), id);
        } catch (Exception e) {
            System.err.println("Bookmark not found with id: " + id);
            return null;
        }
    }

    @Override
    public List<Bookmark> getAllBookmarks() {
        String sql = """
        SELECT * FROM bookmarks
        """;

        return jdbcTemplate.query(sql, mapper.bookmarkRowMapper(newsRepo, gameRepo));
    }

    @Override
    public Bookmark addBookmark(Bookmark bookmark) {
        String sql = """
            INSERT INTO bookmarks (news_id, game_id)
            VALUES (?, ?);
        """;

        try {
            jdbcTemplate.update(sql,
                    (bookmark.getNews() == null ? "" : bookmark.getNews().getId()),
                    (bookmark.getGame() == null ? "" : bookmark.getGame().getId()));
        } catch (Exception e) {
            System.err.println("Error adding bookmark: " + e.getMessage());
        }

        return bookmark;
    }

    @Override
    public void updateBookmark(Bookmark bookmark) {
        String sql = """
        UPDATE bookmarks
        SET news_id = ?, game_id = ?
        WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql,
                    (bookmark.getNews() == null ? "" : bookmark.getNews().getId()),
                    (bookmark.getGame() == null ? "" : bookmark.getGame().getId()),
                    bookmark.getId());
        } catch (Exception e) {
            System.err.println("Error updating bookmark: " + e.getMessage());
        }
    }

    @Override
    public void deleteBookmark(String id) {
        String sql = """
        DELETE FROM bookmarks WHERE id = ?;
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting bookmark: " + e.getMessage());
        }
    }

    @Override
    public void deleteNewsBookmark(String id) {
        String sql = """
        DELETE FROM bookmarks WHERE news_id = ?;
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting bookmark: " + e.getMessage());
        }
    }

    @Override
    public void deleteGameBookmark(String id) {
        String sql = """
        DELETE FROM bookmarks WHERE game_id = ?;
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting bookmark: " + e.getMessage());
        }
    }
}
