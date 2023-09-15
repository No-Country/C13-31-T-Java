'use client';
import { type NextPage } from 'next';
import Layout from '@components/layout';
import TaskCard from '@components/TaskCard';
import { Input } from '@nextui-org/react';
import { SearchIcon } from '@components/SearchIcon';
import { useEffect, useCallback } from 'react';
import useApi from '@hooks/useApi';

const Tasks: NextPage = () => {
    const { getData, data: requests } = useApi('/request/list?criteria=&page=0&size=4');

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
                <div className="my-3">
                    <Input
                        label="Buscar"
                        isClearable
                        radius="lg"
                        classNames={{
                            label: 'bg-white text-black/50 dark:text-white/90',
                            input: [
                                'bg-white',
                                'text-black/90 dark:text-white/90',
                                'placeholder:text-default-700/50 dark:placeholder:text-white/60',
                            ],
                            innerWrapper: 'bg-white',
                            inputWrapper: [
                                'shadow-xl',
                                'bg-white',
                                'dark:bg-white',
                                'backdrop-white-xl',
                                'backdrop-white',
                                'hover:bg-white',
                                'dark:hover:bg-white',
                                'group-data-[focused=true]:bg-white',
                                'dark:group-data-[focused=true]:bg-white',
                                '!cursor-text',
                            ],
                        }}
                        placeholder="Remodelación de baño..."
                        startContent={
                            <SearchIcon className="text-black/50 dark:text-white/90 text-slate-400 pointer-events-none flex-shrink-0" />
                        }
                    />
                </div>
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
