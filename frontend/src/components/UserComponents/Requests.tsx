'use client';
import React, { useState } from 'react';
import { Input, Button, Textarea, Select, SelectItem } from '@nextui-org/react';
import { useFormik } from 'formik';
import useApi from '@hooks/useApi';
import * as Yup from 'yup';
import cldClient from '@libs/cldClient';
import { Poppins } from 'next/font/google';
import Alert from '@components/Alert';
import toast, { Toaster } from 'react-hot-toast';
import { useRouter } from 'next/router';

const poppins = Poppins({ weight: '800', subsets: ['latin-ext'] });

type Value = {
    size?: number;
    type?: string;
    name?: string;
};

const Requests: React.FC = () => {
    const router = useRouter();

    const [lugares] = useState([
        { id: 1, nombre: 'South' },
        { id: 2, nombre: 'West' },
        { id: 3, nombre: 'North' },
        { id: 4, nombre: 'East' },
    ]);
    const { mutationApi, data, error: storeError } = useApi('/client/request/new');

    const handleSubmit = async (values) => {
        const { zoneDTO, description, imagesDTO } = values;
        const zona = lugares.find((lugar) => `${lugar.id}` === zoneDTO);

        try {
            const toastLoading = toast.loading('Publicando solicitud');
            const uploadImages = await cldClient(imagesDTO);

            if (Array.isArray(uploadImages) && uploadImages.length > 0) {
                const valuesRequest = {
                    zoneDTO: zona?.nombre,
                    description: description,
                    imagesDTO: uploadImages,
                };

                await mutationApi('POST', valuesRequest);

                if (data?.status === 201) {
                    toast.dismiss(toastLoading);
                    toast.success('Nueva solicitud registrada');
                } else {
                    toast.error('Error al cargar imágenes');
                }

                storeError && toast.error(storeError);
                // setTimeout(() => {
                router.push('/client/tasks');
                // }, 2000);
            }
        } catch (error) {
            toast.error('Error: ' + error);
        }
    };

    const formik = useFormik({
        initialValues: {
            zoneDTO: '',
            description: '',
            imagesDTO: [''],
        },
        validationSchema: Yup.object({
            zoneDTO: Yup.string().required('Este campo es requerido'),
            description: Yup.string().required('Este campo es requerido'),
            imagesDTO: Yup.array()
                .of(
                    Yup.mixed()
                        .test('fileFormat', 'Formato no válido', (value: Value) => {
                            if (!value) return true;
                            const allowedFormats = ['image/jpeg', 'image/jpg', 'image/png'];
                            return allowedFormats.includes(value.type);
                        })
                        .test('fileSize', 'La imagen es demasiado grande', (value: Value) => {
                            if (!value) return true;
                            const maxSize = 5 * 1024 * 1024;
                            return value.size <= maxSize;
                        }),
                )
                .required('Debes subir al menos una imagen'),
        }),
        onSubmit: (values) => handleSubmit(values),
    });

    const handleChangeImage = (e) => {
        const files = Array.from(e.target.files);
        formik.setFieldValue('imagesDTO', files);
    };

    return (
        <div className="flex flex-row w-full justify-center items-center h-screen">
            <Toaster />
            <div>
                <div className="flex justify-center items-center  bg-workman bg-cover bg-center h-[40rem] w-[25rem] z-10  mx-2 rounded-xl relative cursor-pointer group">
                    <div className="w-full h-full bg-[#71ccff] rounded-xl opacity-80 absolute backd z-20 grayscale-0 group-hover:grayscale transition duration-300 ease-in-out"></div>
                    <h1
                        className={`${poppins.className} text-white text-center text-5xl font-bold opacity-100 z-30 grayscale-0 group-hover:text-[#b4e3ff] group-hover:drop-shadow-md transition duration-300 ease-in-out absolute`}
                    >
                        PUBLICAR SOLICITUD
                    </h1>
                </div>
            </div>
            <div className="flex flex-col">
                <form onSubmit={(e) => formik.handleSubmit(e)}>
                    <Textarea
                        isRequired
                        label="Description"
                        placeholder="Ingrese el detalle de su solicitud que leerán los potenciales prestadores de servicio"
                        className="max-w-xs mb-2"
                        name="description"
                        onChange={(e) => formik.handleChange(e)}
                        onBlur={(e) => formik.handleBlur(e)}
                        value={formik.values.description}
                    />
                    {formik.errors.description && formik.touched.description && (
                        <Alert message={formik.errors.description} />
                    )}

                    <Input
                        type="file"
                        label="Subir Imagen Referencial"
                        placeholder="Ingrese"
                        name="imagesDTO"
                        multiple
                        accept=".jpg, .jpeg, .png"
                        onChange={(e) => handleChangeImage(e)}
                        onBlur={(e) => formik.handleBlur(e)}
                    />
                    {formik.errors.imagesDTO && formik.touched.imagesDTO && (
                        <Alert message={formik.errors.imagesDTO as string} />
                    )}

                    <Select
                        isRequired
                        label="Selecciona una Zona"
                        className="max-w-xs mt-2"
                        name="zoneDTO"
                        onChange={(e) => formik.handleChange(e)}
                        onBlur={(e) => formik.handleBlur(e)}
                        value={formik.values.zoneDTO}
                    >
                        {Array.isArray(lugares) &&
                            lugares.length > 0 &&
                            lugares.map((lugar: { id: number; nombre: string }) => (
                                <SelectItem key={lugar.id} value={lugar.nombre}>
                                    {lugar.nombre}
                                </SelectItem>
                            ))}
                    </Select>
                    {formik.errors.zoneDTO && formik.touched.zoneDTO && (
                        <Alert message={formik.errors.zoneDTO as string} />
                    )}

                    <Button color="primary" className="mt-6" type="submit" disabled={formik.isSubmitting}>
                        Publicar
                    </Button>
                </form>
            </div>
        </div>
    );
};

export default Requests;
