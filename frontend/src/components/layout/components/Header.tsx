import React from 'react';
import Nav from './Nav';

const Header: React.FC = () => {
    return (
        <header className="fixed w-full">
            <Nav />
        </header>
    );
};

export default Header;
