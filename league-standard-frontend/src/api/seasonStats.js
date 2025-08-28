import { API_BASE_URL, API_ENDPOINTS } from "../constants/api";

export const seasonStatsAPI = {
    getSeasonStatsByPlayer: async (playerID) => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.seasonStats}/${playerID}`);

        if (!response.ok) {
            throw new Error("Failed to fetch Season Stats");
        };

        return response.json();
    },
}