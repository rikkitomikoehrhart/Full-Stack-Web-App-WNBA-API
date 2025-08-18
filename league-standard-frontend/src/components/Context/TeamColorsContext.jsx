/* eslint-disable react-refresh/only-export-components */
import { createContext, useContext, useState, useEffect } from "react";
const TeamColorsContext = createContext();

export const useTeamColors = () => {
    const context = useContext(TeamColorsContext);
    if (!context) {
        throw new Error("Team Colors must be used within a Team Colors Provider");
    
    }
    return context;
};

export const TeamColorsProvider = ({ children }) => {
    const [teamColors, setTeamColors] = useState([]);

    const fetchTeamColors = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/colors");
            const teamColors = await response.json();
            setTeamColors(teamColors);
        } catch (err) {
            console.error("Error fetching teamColors: ", err);
        }
    };


    useEffect(() => {
        fetchTeamColors();
    }, []);

    return (
        <TeamColorsContext.Provider value={{ teamColors, fetchTeamColors }}>
            {children}
        </TeamColorsContext.Provider>
    )
}

