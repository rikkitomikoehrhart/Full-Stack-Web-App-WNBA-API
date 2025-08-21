package com.rikkitomikoehrhart.league_standard.data;

import com.rikkitomikoehrhart.league_standard.model.Bookmark;

import java.util.List;

public interface BookmarksRepo {
    public Bookmark getBookmarkByID(String id);
    public List<Bookmark> getAllBookmarks();
    public Bookmark addBookmark(Bookmark bookmark);
    public void updateBookmark(Bookmark bookmark);
    public void deleteBookmark(String id);
    public void deleteNewsBookmark(String id);
    public void deleteGameBookmark(String id);
}
