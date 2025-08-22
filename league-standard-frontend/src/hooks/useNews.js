import { useQuery } from '@tanstack/react-query';
import { newsAPI } from '../api/news';
import { CACHE_TIMES } from '../constants/cache';

export const newsKeys = {
    all: ['news'],
    lists: () => [...newsKeys.all, 'list'],
    details: () => [...newsKeys.all, 'detail'],
    detail: (id) => [...newsKeys.details(), id],
};

export const useNews = () => {
    return useQuery({
        queryKey: newsKeys.lists(),
        queryFn: newsAPI.getNews,
        staleTime: CACHE_TIMES.ONE_DAY,
    });
};

export const useNewsByID = (id) => {
    return useQuery({
        queryKey: newsKeys.detail(id),
        queryFn: () => newsAPI.getNewsByID(id),
        enabled: !!id,
    });
};