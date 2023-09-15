import React from 'react';
import type { NextPage } from 'next';
import Layout from '@components/layout';
import { Request } from '@components/UserComponents';

const Requests: NextPage = () => {
    return (
        <Layout title="Solicitudes">
            <Request />
        </Layout>
    );
};

export default Requests;
