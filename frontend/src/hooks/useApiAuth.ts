import useSWR from 'swr';

const fetcher = (url: string) => fetch(url).then((res) => res.json());

const useApi = (endpoint: string) => {
    const api = process.env.API_URL + endpoint;
    const { data, error, mutate } = useSWR(api, fetcher);

    return {
        data,
        error,
        isLoading: !error && !data,
        mutate,
    };
};

export default useApi;
