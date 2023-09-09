import React from 'react';
import { Card, CardHeader, CardFooter, Button } from '@nextui-org/react';
import Image from 'next/image';
import ImageC from '../assets/images/card-example-6.jpeg';

const TaskCard: React.FC = () => {
    return (
        <Card isFooterBlurred className="w-full h-[300px] sm:my-5 col-span-1 hover:scale-105 cursor-pointer">
            <CardHeader className="absolute z-10 top-1 flex-col items-start">
                <p className="text-tiny text-white/60 uppercase font-bold">New</p>
                <h4 className="text-black font-medium text-2xl">Acme camera</h4>
            </CardHeader>
            <Image
                width={500}
                height={500}
                alt="Card example background"
                className="z-0 w-full h-full scale-125 -translate-y-6 object-cover"
                src={ImageC}
            />
            <CardFooter className="absolute bg-white/30 bottom-0 border-t-1 border-zinc-100/50 z-10 justify-between">
                <div>
                    <p className="text-black text-tiny">Available soon.</p>
                    <p className="text-black text-tiny">Get notified.</p>
                </div>
                <Button className="text-tiny" color="primary" radius="full" size="sm">
                    Notify Me
                </Button>
            </CardFooter>
        </Card>
    );
};

export default TaskCard;
