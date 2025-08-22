import { useQuery } from '@tanstack/react-query';
import { newsAPI } from '../api/news';

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
        staleTime: 24 * 60 * 60 * 1000,
    });
};

export const useNewsByID = (id) => {
    return useQuery({
        queryKey: newsKeys.detail(id),
        queryFn: () => newsAPI.getNewsByID(id),
        enabled: !!id,
    });
};