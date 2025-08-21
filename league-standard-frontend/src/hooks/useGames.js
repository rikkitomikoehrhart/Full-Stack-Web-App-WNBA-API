import { useQuery } from "@tanstack/react-query";
import { gamesAPI } from "../api/games";



export const gamesKeys = {
    all: ['games'],
    lists: () => [...gamesKeys.all, 'list'],
    list: (filters) => [...gamesKeys.list(), { filters }],
    nextByTeam: (teamID) => [...gamesKeys.all, 'team', teamID],
};


export const useGames = () => {
    return useQuery({
        queryKey: gamesKeys.lists(),
        queryFn: gamesAPI.getGames,
        staleTime: 24 * 60 * 60 * 1000,
    });
};

export const useNextGameByTeam = (teamID) => {
    return useQuery({
        queryKey: gamesKeys.nextByTeam(teamID),
        queryFn: () => gamesAPI.getNextTeamGame(teamID),
        enabled: !!teamID,
    });
};