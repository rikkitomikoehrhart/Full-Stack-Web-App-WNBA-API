const API_BASE_URL = 'http://localhost:8080/api';

export const teamsAPI = {
    getTeams: async () => {
        const response = await fetch(`${API_BASE_URL}/teams`);
        if (!response.ok) {
            throw new Error("Failed to fetch teams");
        }
        
        return response.json();
    },
    getTeamsByID: async (id) => {
        const response = await fetch(`${API_BASE_URL}/teams/${id}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch team ${id}`);
        }

        return response.json();
    },
};