package com.rikkitomikoehrhart.league_standard.data;

import com.rikkitomikoehrhart.league_standard.model.News;

import java.util.List;

public interface NewsRepo {
    public News getNewsByID(String id);
    public List<News> getAllNews();
    public News addNews(News news);
    public void updateNews(News news);
    public void deleteNews(String id);
}
