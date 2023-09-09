/* eslint-disable @typescript-eslint/no-explicit-any */
import { useState } from 'react';
import { mutate } from 'swr';
import httpClient from '@libs/httpClient';

const useApi = (auth: boolean) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    let options = {};

    if (auth) {
        options = {
            'Content-Type': 'application/json',
            accept: '*/*',
            Authorization: `Bearer ${localStorage.getItem('token')}`,
        };
    } else {
        options = {
            'Content-Type': 'application/json',
            accept: '*/*',
        };
    }

    const getData = async (endpoint: string, id?: string) => {
        if (!endpoint) {
            throw new Error('Endpoint is required');
        }

        try {
            setIsLoading(true);

            const getEndpoint = id !== undefined ? `${endpoint}/${id}` : endpoint;

            const res = await httpClient(`${getEndpoint}`, { method: 'GET', headers: options });

            if (res.status !== 200) {
                setError(res.statusText);
            }

            if (res.status === 200) {
                setIsLoading(false);
                setData(res.data);
            }

            return data;
        } catch (error) {
            setIsLoading(false);
            setError(error);
            throw new Error(error);
        }
    };

    const mutationApi = async (endpoint: string, method: string, values: any) => {
        if (!endpoint) {
            throw new Error('Endpoint is required');
        }

        try {
            console.log(data);
            const res =
                method === 'POST'
                    ? await httpClient.post(`${endpoint}`, JSON.stringify(values), { headers: options })
                    : await httpClient.put(`${endpoint}`, JSON.stringify(values), { headers: options });

            if (res.status !== 200) {
                setError(res.statusText);
            }

            if (res.status === 200) {
                setIsLoading(false);
                mutate(endpoint, res.data, false);
                return res.data;
            }
        } catch (error) {
            setIsLoading(false);
            setError(error);
        }
    };

    return {
        data,
        error,
        isLoading,
        mutationApi,
        getData,
    };
};

export default useApi;
