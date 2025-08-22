/* eslint-disable react-refresh/only-export-components */
import { createContext, useContext, useState, useEffect } from "react";
import { API_BASE_URL, API_ENDPOINTS } from "../../constants/api";

const FavoritesContext = createContext();


export const useFavorites = () => {
    const context = useContext(FavoritesContext);
    if (!context) {
        throw new Error("useFavorites must be used within a Favorites Provider");
    
    }
    return context;
};

export const FavoritesProvider = ({ children }) => {
    const [favoriteTeams, setFavoriteTeams] = useState([]);
    const [favoriteTeamIDs, setFavoriteTeamIDs] = useState(new Set());
    const [favoritePlayers, setFavoritePlayers] = useState([]);
    const [favoritePlayerIDs, setFavoritePlayerIDs] = useState(new Set());

    const fetchFavorites = async () => {
        try {
            const teamsResponse = await fetch(`${API_BASE_URL}${API_ENDPOINTS.favorites}/teams`);
            const teamFavorites = await teamsResponse.json();
            setFavoriteTeams(teamFavorites);
            setFavoriteTeamIDs(new Set(teamFavorites.map(fav => fav.team.id)));

            const playersResponse = await fetch(`${API_BASE_URL}${API_ENDPOINTS.favorites}/players`);
            const playerFavorites = await playersResponse.json();
            setFavoritePlayers(playerFavorites);
            setFavoritePlayerIDs(new Set(playerFavorites.map(fav => fav.player.id)));
        } catch (err) {
            console.error("Error fetching favorites: ", err);
        }
    };

    const toggleTeamFavorite = async (teamID) => {
        const isFavorite = favoriteTeamIDs.has(teamID);

        try {
            if (isFavorite) {
                await fetch(`${API_BASE_URL}${API_ENDPOINTS.favorites}/teams/${teamID}`, { method: "DELETE" });
            } else {
                await fetch(`${API_BASE_URL}${API_ENDPOINTS.favorites}/teams/${teamID}`, { method: "POST" });
            }

            await fetchFavorites();
        } catch (error) {
            console.error("Error toggling favorite team: ", error);
        }
    };

    const togglePlayerFavorite = async (playerID) => {
        const isFavorite = favoritePlayerIDs.has(playerID);

        try {
            if (isFavorite) {
                await fetch(`${API_BASE_URL}${API_ENDPOINTS.favorites}/players/${playerID}`, { method: "DELETE" });
            } else {
                await fetch(`${API_BASE_URL}${API_ENDPOINTS.favorites}/players/${playerID}`, { method: "POST" });
            }

            await fetchFavorites();
        } catch (error) {
            console.error("Error toggling favorite player: ", error);
        }
    }

    useEffect(() => {
        fetchFavorites();
    }, []);

    return (
        <FavoritesContext.Provider value={{ favoriteTeams, favoritePlayers, favoriteTeamIDs, favoritePlayerIDs, toggleTeamFavorite, togglePlayerFavorite, refreshFavorites: fetchFavorites }}>
            {children}
        </FavoritesContext.Provider>
    )
}

