import { API_BASE_URL, API_ENDPOINTS } from "../constants/api";

export const standingsAPI = {
    getStandings: async () => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.standings}`);
        if (!response.ok) {
            throw new Error("Failed to fetch standings");
        }

        const data = await response.json();

        return data.sort((a, b) => a.league_rank - b.league_rank);
    },
};