import { useQuery } from "@tanstack/react-query";
import { teamsAPI } from "../api/teams";
import { CACHE_TIMES } from "../constants/cache";

export const teamsKeys = {
    all: ['teams'],
    lists: () => [...teamsKeys.all, 'list'],
    list: (filters) => [...teamsKeys.lists(), { filters }],
    details: () => [...teamsKeys.all, 'detail'],
    detail: (id) => [...teamsKeys.details(), id], 
};

export const useTeams = () => {
    return useQuery({
        queryKey: teamsKeys.lists(),
        queryFn: teamsAPI.getTeams,
        staleTime: CACHE_TIMES.ONE_DAY
    });
};

export const useTeamByID = (id) => {
    return useQuery({
        queryKey: teamsKeys.detail(id),
        queryFn: () => teamsAPI.getTeamsByID(id),
        enabled: !!id,
    });
};
