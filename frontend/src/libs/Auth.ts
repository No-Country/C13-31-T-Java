import { type GetServerSidePropsContext } from 'next';

import { getServerSession, type NextAuthOptions, type DefaultSession } from 'next-auth';

import CredentialsProvider from 'next-auth/providers/credentials';
import httpClient from '@libs/httpClient';

declare module 'next-auth' {
    export interface Session extends DefaultSession {
        token: {
            jwt: string;
            user: {
                userName: string;
                lastName: string;
                roles: string[];
            }
        } & DefaultSession['user'];
    }

    interface User {
        user: {
            token: string;
            user: {
                userName: string;
                lastName: string;
                roles: number[];
            }
        }

    }
}

export const authOptions: NextAuthOptions = {
    debug: true,
    providers: [
        CredentialsProvider({
            name: 'credentials',
            credentials: {
                email: { label: 'Email', type: 'text', placeholder: 'pepe@gmail.com' },
                password: { label: 'Password', type: 'text' },
            },
            authorize: async (credentials) => {
                try {
                    const res = await httpClient.post('/auth/login', credentials);

                    const user = await res.data;

                    return res.status === 200 && user && Promise.resolve({ user });
                } catch (error) {
                    return Promise.resolve(null);
                }
            },
        }),
    ],
    session: {
        strategy: 'jwt',
        maxAge: 30 * 24 * 60 * 60, // 30 days
        updateAge: 24 * 60 * 60, // 24 hours
    },
    callbacks: {
        async jwt({ token, user }) {
            const data = user;

            if (user) {
                token.jwt = data.user.token;
                token.user = data.user.user;
            }

            return token;
        },
        async session({ session, token, user }) {
            return { ...session, token, user };
        },
    },
    secret: process.env.NEXTAUTH_SECRET,
};

export const getServerAuthSession = (ctx: {
    req: GetServerSidePropsContext['req'];
    res: GetServerSidePropsContext['res'];
}) => {
    return getServerSession(ctx.req, ctx.res, authOptions);
};
