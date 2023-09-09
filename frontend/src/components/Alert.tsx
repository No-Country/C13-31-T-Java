import React from 'react';
import { Poppins } from 'next/font/google';

interface AlertProps {
    message: string | string[];
}

const poppins = Poppins({ weight: '400', subsets: ['latin-ext'] });

const Alert: React.FC<AlertProps> = ({ message }) => {
    return (
        <div
            className={`${poppins.className} text-xs bg-red-300 p-2 w-full m-1 rounded-md border-1 border-red-400 text-red-600`}
        >
            {message}
        </div>
    );
};

export default Alert;
