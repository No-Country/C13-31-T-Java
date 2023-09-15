'use client';
import StarsUser from '@components/StarsUser';
import React from 'react';

interface ProfileProps {
    user: {
        imageLink: string | null;
        userName: string;
        userLastname: string;
        email: string;
        te: string;
    };
}

const Profile: React.FC<ProfileProps> = ({ user }): JSX.Element => {
    return (
        <>
            <h4 className="font-bold min-w-full text-xl my-4">
                Usuario: {user?.userName} {user?.userLastname}
            </h4>
            <p className=" uppercase font-bold min-w-full text-xl my-4 ">Lima</p>
            <div className="flex  min-w-full text-xl my-4 ">
                <p>Valoraciones: </p> <StarsUser />
            </div>
            <div className="min-w-full text-xl my-4">Nombre:{user?.userName} </div>
            <div className="min-w-full text-xl my-4">Apellido: {user?.userLastname}</div>
            {/* <div className="min-w-full text-xl my-4">Nacionalidad: Polaca</div> */}
            <div className="min-w-full text-xl my-4">Correo: {user?.email}</div>
            <div className="min-w-full text-xl my-4">Telefóno: {user?.te}</div>
        </>
    );
};

export default Profile;
