import { QueryClient } from '@tanstack/react-query';

const ONE_DAY = 24 * 60 * 60 * 1000;


export const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            staleTime: 30 * 1000,
            gcTime: 30 * 1000,
            retry: 2,
            refetchOnWindowFocus: false,
        },
    },
});