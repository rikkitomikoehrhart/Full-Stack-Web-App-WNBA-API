package com.rikkitomikoehrhart.league_standard.model;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class News {
    private String id;
    private String headline;
    private String description;
    private String link;
    private List<String> image_urls;
    private LocalDateTime published;
    private List<Map<String, String>> categories;

    public News() {};
    public News(String id, String headline, String description, String link, List<String> image_urls, LocalDateTime published, List<Map<String, String>> categories) {
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.link = link;
        this.image_urls = image_urls;
        this.published = published;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(List<String> image_urls) {
        this.image_urls = image_urls;
    }

    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    public List<Map<String, String>> getCategories() {
        return categories;
    }

    public void setCategories(List<Map<String, String>> categories) {
        this.categories = categories;
    }
}

