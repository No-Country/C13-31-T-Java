'use client';
import Image from 'next/image';
import { Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button } from '@nextui-org/react';
import Logo from '../../../assets/images/FaT100.svg';
import { Poppins } from 'next/font/google';
import User from './User';
import { useSession } from 'next-auth/react';

const poppins = Poppins({ weight: '400', subsets: ['latin-ext'] });

const Nav: React.FC = () => {
    const { data: session } = useSession();

    return (
        <Navbar className="bg-white g">
            <NavbarBrand>
                <Image src={Logo} alt="FindATrader" width={50} height={50} />
                <p className={`${poppins} font-bold text-inherit`}>FindATrader</p>
            </NavbarBrand>
            <NavbarContent className="hidden sm:flex gap-4" justify="center">
                <NavbarItem className="hidden">
                    <Link color="foreground" href="/">
                        Inicio
                    </Link>
                </NavbarItem>
                <NavbarItem isActive>
                    {session ? (
                        session.token.user.roles[0] == 'Client' ? (
                            <Link href="/client/tasks" aria-current="page">
                                Trabajos
                            </Link>
                        ) : (
                            <Link href="/provider/tasks" aria-current="page">
                                Trabajos
                            </Link>
                        )
                    ) : null}
                </NavbarItem>
                <NavbarItem>
                    <Button
                        href="/client/requests"
                        as={Link}
                        color="success"
                        className="text-white font-bold hover:scale-105"
                        variant="solid"
                    >
                        Publicar
                    </Button>
                </NavbarItem>
            </NavbarContent>
            <NavbarContent justify="end">
                {session ? (
                    <User />
                ) : (
                    <>
                        <NavbarItem className="hidden lg:flex">
                            <Link href="#">Login</Link>
                        </NavbarItem>
                        <NavbarItem>
                            <Button as={Link} color="primary" href="#" variant="flat">
                                Sign Up
                            </Button>
                        </NavbarItem>
                    </>
                )}
            </NavbarContent>
        </Navbar>
    );
};

export default Nav;
