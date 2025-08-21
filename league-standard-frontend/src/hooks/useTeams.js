import { useQuery } from "@tanstack/react-query";
import { teamsAPI } from "../api/teams";

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
        staleTime: 24 * 60 * 60 * 1000
    });
};

export const useTeamByID = (id) => {
    return useQuery({
        queryKey: teamsKeys.detail(id),
        queryFn: () => teamsAPI.getTeamsByID(id),
        enabled: !!id,
    });
};
