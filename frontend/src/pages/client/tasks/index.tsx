'use client';
import { type NextPage } from 'next';
import Layout from '@components/layout';
import TaskCard from '@components/TaskCard';
import useApi from '@hooks/useApi';
import { useEffect, useCallback } from 'react';

const Tasks: NextPage = () => {
    const { getData, data: requests } = useApi('/request/list/paged?page=0&size=4');

    const getRequests = useCallback(async () => {
        try {
            await getData();
        } catch (error) {
            console.error(error);
        }
    }, []);

    useEffect(() => {
        console.log('Requests ' + requests?.totalPages);
    }, [requests]);

    useEffect(() => {
        getRequests();
    }, [getRequests]);

    return (
        <Layout title="Solicitudes">
            <section className="w-full">
                <h1 className="text-white font-bold text-3xl">Solicitudes</h1>
                <div className="my-3"></div>
                <article className="flex flex-col xl:grid xl:grid-rows-4 xl:grid-cols-4 xl:gap-4">
                    {requests?.requests.map((task, index) => {
                        return (
                            <TaskCard
                                fecha={task.date}
                                descripcion={task.description}
                                image={task.images[0].imageLink}
                                key={index}
                            />
                        );
                    })}
                </article>
            </section>
        </Layout>
    );
};

export default Tasks;
