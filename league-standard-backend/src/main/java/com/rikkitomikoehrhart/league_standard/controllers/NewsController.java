package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.NewsRepo;
import com.rikkitomikoehrhart.league_standard.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/news")
public class NewsController {

    @Autowired
    private NewsRepo newsRepo;

    @GetMapping()
    public List<News> getNewsArticles() { return newsRepo.getAllNews(); }

    @GetMapping("/{id}")
    public News getNewsByID(@PathVariable String id) { return newsRepo.getNewsByID(id); }
}
