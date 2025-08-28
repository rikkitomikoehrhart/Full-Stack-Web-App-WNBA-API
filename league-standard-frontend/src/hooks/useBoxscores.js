import { useQuery } from "@tanstack/react-query";
import { boxscoresAPI } from "../api/boxscores";
import { CACHE_TIMES } from "../constants/cache";


export const boxscoresKeys = {
    all: ['boxscores'],
    details: () => [...boxscoresKeys.all, 'detail'],
    detail: (id) => [...boxscoresKeys.details(), id]
};

export const useBoxscoreByGame = (id) => {
    return useQuery({
        queryKey: boxscoresKeys.detail(id),
        queryFn: () => boxscoresAPI.getBoxscoresByGame(id),
        enabled: !!id,
    });
}