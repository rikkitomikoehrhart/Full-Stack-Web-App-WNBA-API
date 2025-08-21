const API_BASE_URL = 'http://localhost:8080/api';

export const playersAPI = {
    getPlayers: async () => {
        const response = await fetch(`${API_BASE_URL}/players`);
        if (!response.ok) {
            throw new Error("Failed to fetch players");
        }
        const data = await response.json();

        return data.sort((a, b) => {
            const lastNameCompare = a.last_name.localeCompare(b.last_name);
            if (lastNameCompare != 0) {
                return lastNameCompare;
            } 
            return a.first_name.localeCompare(b.first_name);
        });
    },
    getPlayer: async (playerID) => {
        const response = await fetch(`${API_BASE_URL}/players/${playerID}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch player ${playerID}`)
        }

        return response.json();
    },
    getPlayersByTeam: async (teamID) => {
        const response = await fetch(`${API_BASE_URL}/players/team/${teamID}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch players for team ${teamID}`);
        }

        return response.json();
    },
};