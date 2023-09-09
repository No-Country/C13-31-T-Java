'use client';
/* eslint-disable react/prop-types */
import '@styles/globals.css';
import { type Session } from 'next-auth';
import { SessionProvider } from 'next-auth/react';
import { AppProps } from 'next/app';
import { NextUIProvider } from '@nextui-org/react';

// Agrega PropTypes o TypeScript para validar las props


const App: React.FC = ({ Component, pageProps }: AppProps<{ session: Session }>) => {
    return (
        <SessionProvider session={pageProps.session} refetchInterval={5 * 60} refetchOnWindowFocus={true}>
            <NextUIProvider>
                <Component {...pageProps} />
            </NextUIProvider>
        </SessionProvider>
    );
};

export default App;