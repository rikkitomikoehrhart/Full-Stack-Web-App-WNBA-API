import { useQuery } from "@tanstack/react-query";
import { standingsAPI } from "../api/standings";
import { CACHE_TIMES } from "../constants/cache";

export const standingsKeys = {
    all: ['standings'],
    lists: () => [...standingsKeys.all, 'list'],
};

export const useStandings = () => {
    return useQuery({
        queryKey: standingsKeys.lists(),
        queryFn: standingsAPI.getStandings,
        staleTime: CACHE_TIMES.ONE_DAY
    });
};