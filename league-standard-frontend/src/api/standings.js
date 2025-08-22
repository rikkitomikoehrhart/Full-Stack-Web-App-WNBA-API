const API_BASE_URL = 'http://localhost:8080/api/standings';

export const standingsAPI = {
    getStandings: async () => {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) {
            throw new Error("Failed to fetch standings");
        }

        const data = await response.json();

        return data.sort((a, b) => a.league_rank - b.league_rank);
    },
};