/* eslint-disable react-refresh/only-export-components */
import { createContext, useContext, useState, useEffect } from "react";
import Loading from "../UI/Loading";
const PlayerContext = createContext();

export const usePlayers = () => {
    const context = useContext(PlayerContext);

    if (!context) {
        throw new Error("usePlayers must be used within a Players Provider");
    }

    return context;
}

export const PlayerProvider = ({ children }) => {
    const [players, setPlayers] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    const fetchPlayers = async () => {
        try {
            fetch("http://localhost:8080/api/players")
            .then(res => res.json())
            .then(data => {
                const sortedData = data.sort((a, b) => {
                    const lastNameCompare = a.last_name.localeCompare(b.last_name);
                    if (lastNameCompare !== 0) {
                        return lastNameCompare;
                    }
                    return a.first_name.localeCompare(b.first_name);
                });
                setPlayers(sortedData);
                setIsLoading(false);
            })
            .catch(err => {
                console.error("Error: ", err);
                setIsLoading(false);
            });
        } catch (err) {
            console.error("Error fetching players: ", err);
        }
    };

    useEffect(() => {
        fetchPlayers();
    }, []);

    if (isLoading) {
        return (
            <Loading />
        )
    }

    return (
        <PlayerContext.Provider value={{ players, refreshPlayers: fetchPlayers }} >
            {children}
        </PlayerContext.Provider>
    )
}