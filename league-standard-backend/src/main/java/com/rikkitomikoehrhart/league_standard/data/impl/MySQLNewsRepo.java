package com.rikkitomikoehrhart.league_standard.data.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikkitomikoehrhart.league_standard.data.NewsRepo;
import com.rikkitomikoehrhart.league_standard.data.mappers.NewsRowMapper;
import com.rikkitomikoehrhart.league_standard.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLNewsRepo implements NewsRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NewsRowMapper mapper = new NewsRowMapper();
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public News getNewsByID(String id) {
        String sql = """
        SELECT * FROM news WHERE id = ?;
        """;

        try {
            return jdbcTemplate.queryForObject(sql, mapper.newsRowMapper(), id);
        } catch (Exception e) {
            System.err.println("News article not found with id: " + id);
            return null;
        }
    }

    @Override
    public List<News> getAllNews() {
        String sql = """
        SELECT * FROM news;
        """;

        return jdbcTemplate.query(sql, mapper.newsRowMapper());
    }

    @Override
    public News addNews(News news) {
        String sql = """
        INSERT INTO news (headline, description, link, image_urls, published, categories)
        VALUES (?, ?, ?, ?, ?, ?);
        """;

        try {
            jdbcTemplate.update(sql,
                    news.getHeadline(),
                    news.getDescription(),
                    news.getLink(),
                    objectMapper.writeValueAsString(news.getImage_urls()),
                    news.getPublished(),
                    objectMapper.writeValueAsString(news.getCategories()));
        } catch (Exception e) {
            System.err.println("Error adding news item: " + e.getMessage());
        }

        return news;
    }

    @Override
    public void updateNews(News news) {
        String sql = """
        UPDATE news 
        SET headline = ?, description = ?, link = ?, image_urls = ?, published = ?, categories = ?)
        WHERE id = ?
        """;

        try {
            jdbcTemplate.update(sql,
                    news.getHeadline(),
                    news.getDescription(),
                    news.getLink(),
                    objectMapper.writeValueAsString(news.getImage_urls()),
                    news.getPublished(),
                    objectMapper.writeValueAsString(news.getCategories()),
                    news.getId());
        } catch (Exception e) {
            System.err.println("Error updating news item: " + e.getMessage());
        }

    }

    @Override
    public void deleteNews(String id) {
        String sql = """
        DELETE FROM news WHERE id = ?;
        """;

        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error deleting news: " + e.getMessage());
        }
    }
}
