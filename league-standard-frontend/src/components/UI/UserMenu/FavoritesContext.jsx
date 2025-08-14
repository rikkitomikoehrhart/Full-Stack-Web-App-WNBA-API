import { createContext, userContext, useState, useEffect, useContext } from "react";

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

    const fetchFavorites = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/favorites/teams");
            const favorites = await response.json();
            setFavoriteTeams(favorites);
            setFavoriteTeamIDs(new Set(favorites.map(fav => fav.team.id)));
        } catch (err) {
            console.error("Error fetching favorites: ", err);
        }
    };

    const toggleFavorite = async (teamID) => {
        const isFavorite = favoriteTeamIDs.has(teamID);

        try {
            if (isFavorite) {
                await fetch(`http://localhost:8080/api/favorites/teams/${teamID}`, { method: "DELETE" });
            } else {
                await fetch(`http://localhost:8080/api/favorites/teams/${teamID}`, { method: "POST" });
            }

            await fetchFavorites();
        } catch (error) {
            console.error("Error toggling favorite: ", error);
        }
    };

    useEffect(() => {
        fetchFavorites();
    }, []);

    return (
        <FavoritesContext.Provider value={{ favoriteTeams, favoriteTeamIDs, toggleFavorite, refreshFavorites: fetchFavorites}}>
            {children}
        </FavoritesContext.Provider>
    )
}

