const API_BASE_URL = 'http://localhost:8080/api';

export const gamesAPI = {
    getGames: async () => {
        const response = await fetch(`${API_BASE_URL}/games`);
        if (!response.ok) {
            throw new Error("Failed to fetch games");
        }

        const gameData = await response.json();

        const sortedGames = gameData.sort((a, b) =>
            new Date(a.scheduled) - new Date(b.scheduled)
        );

        return sortedGames;
    },
    getNextTeamGame: async (id) => {
        const response = await fetch(`${API_BASE_URL}/games/next/${id}`);
        if (!response.ok) {
            throw new Error ("Failed to fetch next game");
        }

        return response.json();
    },
};