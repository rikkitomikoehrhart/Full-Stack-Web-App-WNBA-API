import { useQuery } from "@tanstack/react-query";
import { teamColorsAPI } from "../api/teamColors";
import { CACHE_TIMES } from "../constants/cache";

export const teamColorsKey = {
    all: ['colors'],
    colors: () => [...teamColorsKey.all, 'colors'],
};

export const useTeamColors = () => {
    return useQuery({
        queryKey: teamColorsKey.colors(),
        queryFn: teamColorsAPI.getColors,
        staleTime: CACHE_TIMES.ONE_DAY
    });
};