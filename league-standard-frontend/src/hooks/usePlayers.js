import { useQuery } from "@tanstack/react-query";
import { playersAPI } from "../api/players";
import { CACHE_TIMES } from "../constants/cache";

export const playersKeys = {
    all: ['players'],
    lists: () => [...playersKeys.all, 'list'],
    list: (filters) => [...playersKeys.lists(), { filters }],
    details: () => [...playersKeys.all, 'detail'],
    detail: (id) => [...playersKeys.details(), id],
    byTeam: (teamID) => [...playersKeys.all, 'team', teamID],
};

export const usePlayers = () => {
    return useQuery({
        queryKey: playersKeys.lists(),
        queryFn: playersAPI.getPlayers,
        staleTime: CACHE_TIMES.ONE_DAY
    });
};

export const usePlayer = (playerID) => {
    return useQuery({
        queryKey: playersKeys.detail(playerID),
        queryFn: () => playersAPI.getPlayer(playerID),
        enabled: !!playerID,
    });
};

export const usePlayersByTeam = (teamID) => {
    return useQuery({
        queryKey: playersKeys.byTeam(teamID),
        queryFn: () => playersAPI.getPlayersByTeam(teamID),
        enabled: !!teamID,
    });
};