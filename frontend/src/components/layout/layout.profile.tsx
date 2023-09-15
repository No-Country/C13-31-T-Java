import React from 'react';
import { Card, CardHeader, CardBody, Image, Listbox, ListboxItem } from '@nextui-org/react';
import { useProfileSlice } from '@stores/profiles';
interface PropsLayout {
    children: React.ReactNode;
    user: {
        imageLink: string | null;
        userName: string;
        userLastname: string;
        email: string;
        te: string;
    };
}

const Layout: React.FC<PropsLayout> = ({ children, user }) => {
    const setIsState = useProfileSlice((state) => state.setIsState);

    const setTab = (key) => {
        setIsState(key, true);
    };

    return (
        <div className="max-w h-full">
            <div className="flex items-center space-x-4 text-small h-full">
                <Card className="py-4 w-1/5 h-full">
                    <CardBody className="overflow-visible py-2">
                        <Image
                            isZoomed
                            alt="Card background"
                            className="object-cover rounded-xl"
                            src={user?.imageLink}
                            width="w1/5"
                        />
                    </CardBody>
                    <Listbox aria-label="Actions" onAction={(key: string) => setTab(key as string)}>
                        <ListboxItem key="isProfile">Perfil</ListboxItem>
                        <ListboxItem key="isPassowrd">Contraseña</ListboxItem>
                        <ListboxItem key="isRequest">Solicitudes</ListboxItem>
                        <ListboxItem key="isResult">Resultados</ListboxItem>
                    </Listbox>
                </Card>
                <Card className="py-10 w-4/5">
                    <CardHeader className="pb-0 pt-2 px-4 flex-col my-36 ml-10">{children}</CardHeader>
                </Card>
            </div>
        </div>
    );
};

export default Layout;
