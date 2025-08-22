import { API_BASE_URL, API_ENDPOINTS } from "../constants/api";

export const teamsAPI = {
    getTeams: async () => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.teams}`);
        if (!response.ok) {
            throw new Error("Failed to fetch teams");
        }
        
        return response.json();
    },
    getTeamsByID: async (id) => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.teams}/${id}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch team ${id}`);
        }

        return response.json();
    },
};