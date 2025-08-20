package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.NewsRepo;
import com.rikkitomikoehrhart.league_standard.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/news")
public class NewsController {

    @Autowired
    private NewsRepo newsRepo;

    @GetMapping()
    public List<News> getNewsArticles() { return newsRepo.getAllNews(); }
}
