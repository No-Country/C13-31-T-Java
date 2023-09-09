'use client';
import React from 'react';
import { Dropdown, DropdownTrigger, DropdownMenu, DropdownItem, User, Link } from '@nextui-org/react';
import { signOut } from 'next-auth/react';
import { useSession } from 'next-auth/react';

const UserMenu: React.FC = () => {
    const { data: session } = useSession();
    const { user } = session?.token || {};
    const { lastName, userName, roles } = user;

    return (
        <div className="flex items-center gap-4">
            <Dropdown placement="bottom-start">
                <DropdownTrigger>
                    <User
                        as="button"
                        avatarProps={{
                            isBordered: true,
                            src: 'https://i.pravatar.cc/150?u=a042581f4e29026024d',
                        }}
                        className="transition-transform"
                        description={`${roles[0]}`}
                        name={`${userName} ${lastName}`}
                    />
                </DropdownTrigger>
                <DropdownMenu aria-label="User Actions" variant="flat">
                    <DropdownItem key="settings">
                        <Link href="/profiles/user">Mi Perfil</Link>
                    </DropdownItem>
                    <DropdownItem key="logout" color="danger" onClick={() => signOut()}>
                        Cerrar Sesión
                    </DropdownItem>
                </DropdownMenu>
            </Dropdown>
        </div>
    );
};

export default UserMenu;
