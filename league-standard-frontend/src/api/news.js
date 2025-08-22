import { API_BASE_URL, API_ENDPOINTS } from "../constants/api";


export const newsAPI = {
    getNews: async () => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.news}`);
        if (!response.ok) {
            throw new Error("Failed to fetch news");
        }

        return response.json()
    },
    getNewsByID: async (id) => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.news}${id}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch news article: ${id}`);
        }
        
        return response.json();
    }
};