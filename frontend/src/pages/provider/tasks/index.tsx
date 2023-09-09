'use client';
import { type NextPage } from 'next';
import Layout from '@components/layout';
import TaskCard from '@components/TaskCard';
import { Input } from '@nextui-org/react';
import { SearchIcon } from '@components/SearchIcon';
const Tasks: NextPage = () => {
    const tasks = [
        { 1: 'Task 1' },
        { 2: 'Task 2' },
        { 3: 'Task 3' },
        { 4: 'Task 4' },
        { 5: 'Task 5' },
        { 6: 'Task 6' },
        { 7: 'Task 7' },
        { 8: 'Task 8' },
        { 9: 'Task 9' },
        { 10: 'Task 10' },
        { 11: 'Task 1' },
        { 12: 'Task 2' },
        { 13: 'Task 3' },
        { 14: 'Task 4' },
        { 15: 'Task 5' },
        { 16: 'Task 6' },
        { 17: 'Task 7' },
        { 18: 'Task 8' },
        { 19: 'Task 9' },
        { 20: 'Task 10' },
        { 21: 'Task 1' },
        { 22: 'Task 2' },
        { 23: 'Task 3' },
        { 24: 'Task 4' },
        { 25: 'Task 5' },
        { 26: 'Task 6' },
        { 27: 'Task 7' },
        { 28: 'Task 8' },
        { 29: 'Task 9' },
        { 30: 'Task 10' },
        { 31: 'Task 10' },
        { 32: 'Task 10' },
    ];

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
                    {tasks.map((task, index) => {
                        return <TaskCard key={index} />;
                    })}
                </article>
            </section>
        </Layout>
    );
};

export default Tasks;
