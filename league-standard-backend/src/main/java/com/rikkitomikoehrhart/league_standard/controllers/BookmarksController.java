package com.rikkitomikoehrhart.league_standard.controllers;

import com.rikkitomikoehrhart.league_standard.data.BookmarksRepo;
import com.rikkitomikoehrhart.league_standard.data.GameRepo;
import com.rikkitomikoehrhart.league_standard.data.NewsRepo;
import com.rikkitomikoehrhart.league_standard.model.Bookmark;
import com.rikkitomikoehrhart.league_standard.model.Game;
import com.rikkitomikoehrhart.league_standard.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/bookmarks")
public class BookmarksController {


    @Autowired
    private BookmarksRepo bookmarksRepo;

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private GameRepo gameRepo;

    @PostMapping("/news/{id}")
    public ResponseEntity<String> addNewsBookmark(@PathVariable String id) {
        News news = newsRepo.getNewsByID(id);
        Bookmark bookmark = new Bookmark();
        bookmark.setNews(news);

        bookmarksRepo.addBookmark(bookmark);
        return ResponseEntity.ok("News Article added to Bookmarks");
    }

    @PostMapping("/games/{id}")
    public ResponseEntity<String> addGamesBookmark(@PathVariable String id) {
        Game game = gameRepo.getGameByID(id);
        Bookmark bookmark = new Bookmark();
        bookmark.setGame(game);

        bookmarksRepo.addBookmark(bookmark);
        return ResponseEntity.ok("Game added to Bookmarks");
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity<String> removeNewsBookmark(@PathVariable String id) {
        bookmarksRepo.deleteNewsBookmark(id);
        return ResponseEntity.ok("News Article removed from Bookmarks");
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<String> removeGameBookmark(@PathVariable String id) {
        bookmarksRepo.deleteGameBookmark(id);
        return ResponseEntity.ok("Game removed from Bookmarks");
    }

    @GetMapping()
    public List<Bookmark> getAllBookmarks() {
        return bookmarksRepo.getAllBookmarks();
    }

}
