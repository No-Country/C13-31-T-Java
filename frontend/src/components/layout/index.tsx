'use client';
import React, { useEffect } from 'react';
import Head from 'next/head';
import { useSession } from 'next-auth/react';
import Header from './components/Header';
import { useRouter } from 'next/router';

interface LayoutProps {
    children: React.ReactNode;
    title: string;
}

const Layout: React.FC<LayoutProps> = ({ children, title }) => {
    const { status } = useSession();

    const router = useRouter();

    useEffect(() => {
        status === 'unauthenticated' && router.push('/auth/signin');
    }, [status, router]);

    return (
        <>
            <Head>
                <title>FaT | {title}</title>
                <meta name="description" content="ACME" />
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <Header />
            <main className="flex h-full w-full p-5 bg-white">{children}</main>
        </>
    );
};

export default Layout;
