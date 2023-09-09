import NextAuth from 'next-auth';
import { authOptions } from '@libs/Auth';

export default NextAuth(authOptions);
