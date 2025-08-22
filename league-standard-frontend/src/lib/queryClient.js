import { CACHE_TIMES } from '../constants/cache';
import { QueryClient } from '@tanstack/react-query';




export const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            staleTime: CACHE_TIMES.ONE_DAY,
            gcTime: CACHE_TIMES.FIVE_MINS,
            retry: 2,
            refetchOnWindowFocus: false,
        },
    },
});