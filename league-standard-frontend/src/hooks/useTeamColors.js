import { useQuery } from "@tanstack/react-query";
import { teamColorsAPI } from "../api/teamColors";

export const teamColorsKey = {
    all: ['colors'],
    colors: () => [...teamColorsKey.all, 'colors'],
};

export const useTeamColors = () => {
    return useQuery({
        queryKey: teamColorsKey.colors(),
        queryFn: teamColorsAPI.getColors,
        staleTime: 24 * 60 * 60 * 1000 
    });
};