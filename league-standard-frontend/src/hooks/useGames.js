import { useQuery } from "@tanstack/react-query";
import { gamesAPI } from "../api/games";
import { CACHE_TIMES } from "../constants/cache";



export const gamesKeys = {
    all: ['games'],
    lists: () => [...gamesKeys.all, 'list'],
    list: (filters) => [...gamesKeys.list(), { filters }],
    details: () => [...gamesKeys.all, 'detail'],
    detail: (id) => [...gamesKeys.details(), id],
    nextByTeam: (teamID) => [...gamesKeys.all, 'team', teamID],
};


export const useGames = () => {
    return useQuery({
        queryKey: gamesKeys.lists(),
        queryFn: gamesAPI.getGames,
        staleTime: CACHE_TIMES.ONE_DAY,
    });
};

export const useGame = (gameID) => {
    return useQuery({
        queryKey: gamesKeys.detail(gameID),
        queryFn: () => gamesAPI.getGame(gameID),
        enabled: !!gameID,
    });
};

export const useNextGameByTeam = (teamID) => {
    return useQuery({
        queryKey: gamesKeys.nextByTeam(teamID),
        queryFn: () => gamesAPI.getNextTeamGame(teamID),
        enabled: !!teamID,
    });
};