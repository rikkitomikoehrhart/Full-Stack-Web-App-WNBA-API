import { useQuery } from "@tanstack/react-query";
import { standingsAPI } from "../api/standings";

export const standingsKeys = {
    all: ['standings'],
    lists: () => [...standingsKeys.all, 'list'],
};

export const useStandings = () => {
    return useQuery({
        queryKey: standingsKeys.lists(),
        queryFn: standingsAPI.getStandings,
        staleTime: 24 * 60 * 60 * 1000,
    });
};