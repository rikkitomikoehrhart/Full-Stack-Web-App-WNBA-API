import { API_BASE_URL, API_ENDPOINTS } from "../constants/api";

export const boxscoresAPI = {
    getBoxscoresByGame: async (gameID) => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.boxscores}/game/${gameID}`);
        if (!response.ok) {
            throw new Error("Failed to fetch Boxscore");
        };

        return response.json();
    },
}