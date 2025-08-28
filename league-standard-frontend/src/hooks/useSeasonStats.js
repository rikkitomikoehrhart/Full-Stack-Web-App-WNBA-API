import { useQuery } from "@tanstack/react-query";
import { seasonStatsAPI } from "../api/seasonStats";


export const seasonStatsKeys = {
    all: ['seasonStats'],
    details: () => [...seasonStatsKeys.all, 'detail'],
    detail: (id) => [...seasonStatsKeys.details(), id]
};

export const useSeasonStatsByPlayer = (id) => {
    return useQuery({
        queryKey: seasonStatsKeys.detail(id),
        queryFn: () => seasonStatsAPI.getSeasonStatsByPlayer(id),
        enabled: !!id,
    });
}