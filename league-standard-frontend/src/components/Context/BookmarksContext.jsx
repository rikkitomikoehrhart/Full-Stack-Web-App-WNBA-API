/* eslint-disable react-refresh/only-export-components */
import { createContext, useContext, useState, useEffect } from "react";
const BookmarksContext = createContext();
import { API_BASE_URL, API_ENDPOINTS } from "../../constants/api";

export const useBookmarks = () => {
    const context = useContext(BookmarksContext);

    if (!context) {
        throw new Error("useBookmarks must be used within a Bookmarks Provider");
    }

    return context;
}

export const BookmarksProvider = ({ children }) => {
    const [bookmarks, setBookmarks] = useState([]);
    const [bookmarksIDs, setBookmarksID] = useState(new Set());

    const fetchBookmarks = async () => {
        try {
            const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.bookmarks}`);
            const jsonResponse = await response.json();

            setBookmarks(jsonResponse);
            setBookmarksID(new Set(jsonResponse.map(bookmark => bookmark.id)));
        } catch (error) {
            console.error("Error fetching bookmarks: ", error);
        }
    };

    const toggleNewsBookmark = async (id) => {
        const isBookmarked = bookmarks.find(b => b.news && b.news.id == id);

        try {
            if (isBookmarked) {
                await fetch(`${API_BASE_URL}${API_ENDPOINTS.bookmarks}/news/${id}`, { method: "DELETE" });
            } else {
                await fetch(`${API_BASE_URL}${API_ENDPOINTS.bookmarks}/news/${id}`, { method: "POST" });
            }

            await fetchBookmarks();
        } catch (error) {
            console.error("Error toggling news bookmark: ", error);
        }

    }

    const toggleGameBookmark = async (id) => {
        const isBookmarked = bookmarks.find(b => b.game && b.game.id == id);

        try {
            if (isBookmarked) {
                await fetch(`${API_BASE_URL}${API_ENDPOINTS.bookmarks}/games/${id}`, { method: "DELETE" });
            } else {
                await fetch(`${API_BASE_URL}${API_ENDPOINTS.bookmarks}/games/${id}`, { method: "POST" });
            }

            await fetchBookmarks();
        } catch (error) {
            console.error("Error fetching game bookmarks: ", error);
        }
    }

    useEffect(() => {
        fetchBookmarks();
    }, []);

    return (
        <BookmarksContext.Provider value={{ bookmarks, bookmarksIDs, toggleNewsBookmark, toggleGameBookmark, refreshBookmarks: fetchBookmarks }}>
            {children}
        </BookmarksContext.Provider>
    )
}