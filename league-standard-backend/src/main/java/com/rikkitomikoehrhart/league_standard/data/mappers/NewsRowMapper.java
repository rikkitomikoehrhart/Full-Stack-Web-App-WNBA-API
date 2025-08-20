package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikkitomikoehrhart.league_standard.model.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsRowMapper {

    public RowMapper<News> newsRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            News news = new News();

            news.setId(rs.getString("id"));
            news.setHeadline(rs.getString("headline"));
            news.setDescription(rs.getString("description"));
            news.setLink(rs.getString("link"));

            String imageUrlsJson = rs.getString("image_urls");
            if (imageUrlsJson != null && !imageUrlsJson.isEmpty()) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<String> imageUrls = objectMapper.readValue(imageUrlsJson, new TypeReference<List<String>>() {
                    });
                    news.setImage_urls(imageUrls);
                } catch (Exception e) {
                    news.setImage_urls(new ArrayList<>());
                }
            } else {
                news.setImage_urls(new ArrayList<>());
            }


            Timestamp timestamp = rs.getTimestamp("published");
            if (timestamp != null) {
                news.setPublished(timestamp.toLocalDateTime());
            }


            String categoriesJson = rs.getString("categories");
            if (categoriesJson != null && !categoriesJson.isEmpty()) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Map<String, String>> categories = objectMapper.readValue(categoriesJson, new TypeReference<List<Map<String, String>>>() {});

                    news.setCategories(categories);
                } catch (Exception e) {
                    news.setCategories(new ArrayList<>());
                }
            } else {
                news.setCategories(new ArrayList<>());
            }

            return news;
        };
    }
}
