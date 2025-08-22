const API_BASE_URL = 'http://localhost:8080/api/news';

export const newsAPI = {
    getNews: async () => {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) {
            throw new Error("Failed to fetch news");
        }

        return response.json()
    },
    getNewsByID: async (id) => {
        const response = await fetch(`${API_BASE_URL}/${id}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch news article: ${id}`);
        }
        
        return response.json();
    }
};