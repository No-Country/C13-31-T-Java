'use client';
/* eslint-disable react-hooks/rules-of-hooks */
/* eslint-disable @typescript-eslint/no-unused-vars */
import type { NextPage } from 'next';
import LayoutProfile from '@components/layout/layout.profile';
import Layout from '@components/layout';
import { Password, Results, Profile } from '@components/UserComponents';
import { useProfileSlice } from '@stores/profiles';
import useApi from '@hooks/useApi';
import { useEffect, useCallback } from 'react';
import toast, { Toaster } from 'react-hot-toast';

const profile: NextPage = () => {
    const isProfile = useProfileSlice((state) => state.isProfile);
    const isPassword = useProfileSlice((state) => state.isPassword);
    const isRequests = useProfileSlice((state) => state.isRequests);
    const isResults = useProfileSlice((state) => state.isResults);

    const { getData, data: usuario, isLoading } = useApi('/details');

    const getUsuario = useCallback(async () => {
        try {
            await getData();
        } catch (error) {
            console.error(error);
        }
    }, []);

    isLoading && toast.loading('Cargando perfil');

    if (usuario) toast.success('Perfil cargado correctamente');

    useEffect(() => {
        getUsuario();
    }, [getUsuario]);

    return (
        <Layout
            title={`${
                isProfile ? 'Perfil' : isPassword ? 'Password' : isRequests ? 'Requests' : isResults && 'Resultados'
            } `}
        >
            <div className="mx-auto my-[5rem]">
                <LayoutProfile user={usuario}>
                    {!isLoading && <Profile user={usuario} />}
                    {isPassword && <Password />}
                    {isRequests && <Results />}
                </LayoutProfile>
            </div>
        </Layout>
    );
};

export default profile;
