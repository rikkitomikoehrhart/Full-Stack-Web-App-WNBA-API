import { API_BASE_URL, API_ENDPOINTS } from "../constants/api";

export const gamesAPI = {
    getGames: async () => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.games}`);
        if (!response.ok) {
            throw new Error("Failed to fetch games");
        }

        const gameData = await response.json();

        const sortedGames = gameData.sort((a, b) => {
            const dateA = new Date(a.scheduled);
            const dateB = new Date(b.scheduled);
            return dateA - dateB;
        });

        return sortedGames;
    },
    getGame: async (gameID) => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.games}/${gameID}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch player ${gameID}`)
        }

        return response.json();
    },
    getNextTeamGame: async (id) => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.games}/next/${id}`);
        if (!response.ok) {
            throw new Error ("Failed to fetch next game");
        }

        return response.json();
    },
};