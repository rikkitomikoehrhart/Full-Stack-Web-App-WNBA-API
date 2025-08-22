import { API_BASE_URL, API_ENDPOINTS } from "../constants/api";


export const teamColorsAPI = {
    getColors: async () => {
        const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.colors}`);
        if (!response.ok) {
            throw new Error("Failed to fetch colors");
        }

        return response.json();
    },
};