const API_BASE_URL = 'http://localhost:8080/api/colors';

export const teamColorsAPI = {
    getColors: async () => {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) {
            throw new Error("Failed to fetch colors");
        }

        return response.json();
    },
};