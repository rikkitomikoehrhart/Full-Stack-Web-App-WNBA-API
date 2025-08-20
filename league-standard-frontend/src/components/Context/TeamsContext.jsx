/* eslint-disable react-refresh/only-export-components */
import { createContext, useContext, useState, useEffect } from "react";
import Loading from '../UI/Loading';
const TeamsContext = createContext();

export const useTeams = () => {
    const context = useContext(TeamsContext);

    if (!context) {
        throw new Error("useTeams must be used within a Teams Provider");
    }

    return context;
};

export const TeamsProvider = ({ children }) => {
    const [teams, setTeams] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
  
    const fetchTeams = async () => {
        try {
            fetch("http://localhost:8080/api/teams")
            .then(res => res.json())
            .then(data => {
                setTeams(data);
                setIsLoading(false);
            })
            .catch(err => {
                console.error("Error: ", err);
                setIsLoading(false);
            });
        } catch (err) {
            console.error("Error fetching teams: ", err);
        }
    };

    useEffect(() => {
        fetchTeams();
    }, []);

    if (isLoading) {
        return (
            <Loading />
        )
    }

    return (
        <TeamsContext.Provider value={{ teams, refreshTeams: fetchTeams }} >
            {children}
        </TeamsContext.Provider>
    )
}

