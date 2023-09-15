'use client';
/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { useEffect } from 'react';
import { type NextPage } from 'next';
import Image from 'next/image';
import Link from 'next/link';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { Poppins } from 'next/font/google';
import useApi from '@hooks/useApi';
import { Input, Checkbox, Button, Select, SelectItem } from '@nextui-org/react';
import Alert from '@components/Alert';
import Head from 'next/head';
import Logo from '../../../assets/images/findatrader.png';
import { SpinnerCircularFixed } from 'spinners-react';
import toast, { Toaster } from 'react-hot-toast';

const poppins = Poppins({ weight: '400', subsets: ['latin-ext'] });

interface SignUp {
    userName: string;
    userLastname: string;
    email: string;
    birthDate: string;
    te: string;
    password: string;
    repassword?: string;
    roles: number[] | string[];
    areas: number[] | string[];
    acceptTerms?: boolean;
}

const SignUp: NextPage = () => {
    const { getData, data: areaList, error, isLoading } = useApi('/area/list');
    const { mutationApi, error: regError, isLoading: isLoadinReg, data: regData } = useApi('/auth/register');

    useEffect(() => {
        getData();
    }, []);

    const handleSubmit = async (values: SignUp) => {
        const registrarUsuario = async (values: SignUp) => {
            await mutationApi('POST', values);
        };
        const { birthDate } = values;
        const convertDate = new Date(birthDate);

        const dataSet = {
            ...values,
            birthDate: convertDate.toISOString(),
            te: values.te.toString(),
            areas: [1],
            roles: [Number(values.roles)],
        };

        const { repassword, acceptTerms, ...finalDataSet } = dataSet;

        registrarUsuario(finalDataSet);

        if (regData.status === 201) {
            toast.success('Usuario registrado');
        }

        regError && toast.error('Error al registrar usuario' + regError);
    };

    const formik = useFormik({
        initialValues: {
            userName: '',
            userLastname: '',
            email: '',
            password: '',
            repassword: '',
            birthDate: '',
            te: '',
            areas: ['0'],
            roles: [''],
            acceptTerms: false,
        },
        validationSchema: Yup.object({
            userName: Yup.string()
                .min(3, 'El nombre debe tener al menos 3 caracteres')
                .max(15, 'El nombre debe tener como maximo 15 caracteres')
                .required('El nombre es requerido'),
            userLastname: Yup.string()
                .min(3, 'El apellido debe tener al menos 3 caracteres')
                .max(15, 'El apellido debe tener como maximo 15 caracteres')
                .required('El apellido es requerido'),
            birthDate: Yup.string().required('La fecha de nacimiento es requerida'),
            email: Yup.string()
                .email('El correo electronico no es valido')
                .required('El correo electronico es requerido'),
            password: Yup.string()
                .min(8, 'La contraseña debe tener al menos 8 caracteres')
                .max(20, 'La contraseña debe tener como maximo 20 caracteres')
                .required('La contraseña es requerida'),
            repassword: Yup.string()
                .min(8, 'La contraseña debe tener al menos 8 caracteres')
                .max(20, 'La contraseña debe tener como maximo 20 caracteres')
                .required('La contraseña es requerida')
                .oneOf([Yup.ref('password')], 'Las contraseñas deben coincidir'),
            te: Yup.string()
                .min(10, 'El telefono debe tener al menos 10 caracteres')
                .max(10, 'El telefono debe tener como maximo 10 caracteres')
                .required('El telefono es requerido'),
            areas: Yup.number().required('Por favor, selecciona un area'),
            roles: Yup.number().required('Por favor, selecciona un rol'),
            acceptTerms: Yup.boolean()
                .required('Debes aceptar los terminos y condiciones')
                .oneOf([true], 'Debes aceptar los terminos y condiciones'),
        }),
        onSubmit: (values) => handleSubmit(values),
    });

    const roles = [
        { id: '4', value: 'Cliente' },
        { id: '5', value: 'Provedor' },
    ];

    const Loading = () => (
        <div className="flex justify-center items-center absolute bg-black w-full h-screen z-50 bg-opacity-30">
            <SpinnerCircularFixed
                size={85}
                thickness={100}
                speed={100}
                color="rgba(100, 201, 254, 1)"
                secondaryColor="rgba(0, 0, 0, 0.44)"
            />
        </div>
    );

    return (
        <>
            <Head>
                <title>FaT | Registrarse</title>
            </Head>
            <section className="w-full h-screen">
                <Toaster />
                {isLoadinReg && <Loading />}
                <article className="flex justify-center items-center w-full h-full">
                    <div>
                        <div className="flex flex-row justify-between items-center">
                            <h1 className={`${poppins.className}`}>Registrarse</h1>
                            <Image src={Logo} alt="fat logo" width={250} />
                        </div>
                        <form
                            className="flex flex-col justify-between items-center p-5 w-[25rem] h-[40rem]"
                            onSubmit={(e) => formik.handleSubmit(e)}
                        >
                            <Input
                                isRequired
                                type="text"
                                name="userName"
                                id="userName"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                label="Nombre"
                                placeholder="Nombre"
                                className={`${poppins.className} max-x-xs`}
                                value={formik.values.userName}
                            />
                            {formik.errors.userName && formik.touched.userName && (
                                <Alert message={formik.errors.userName} />
                            )}
                            <Input
                                isRequired
                                type="text"
                                name="userLastname"
                                id="userLastname"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                label="Apellido"
                                placeholder="Apellido"
                                className={`${poppins.className} max-x-xs`}
                                value={formik.values.userLastname}
                            />
                            {formik.errors.userLastname && formik.touched.userLastname && (
                                <Alert message={formik.errors.userLastname} />
                            )}
                            <Input
                                isRequired
                                type="date"
                                name="birthDate"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                label="Fecha de nacimiento"
                                placeholder="25/05/1997"
                                className={`${poppins.className} max-x-xs`}
                                value={formik.values.birthDate}
                            />
                            {formik.errors.birthDate && formik.touched.birthDate && (
                                <Alert message={formik.errors.birthDate} />
                            )}
                            <Input
                                isRequired
                                type="email"
                                name="email"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                label="Email"
                                placeholder="Correo electronico"
                                className={`${poppins.className} max-x-xs`}
                                value={formik.values.email}
                            />
                            {formik.errors.email && formik.touched.email && <Alert message={formik.errors.email} />}
                            <Input
                                isRequired
                                type="password"
                                name="password"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                label="Contraseña"
                                placeholder="Contraseña"
                                className={`${poppins.className} max-x-xs`}
                                value={formik.values.password}
                            />
                            {formik.errors.password && formik.touched.password && (
                                <Alert message={formik.errors.password} />
                            )}
                            <Input
                                isRequired
                                type="password"
                                name="repassword"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                id="repassword"
                                label="Repetir contraseña"
                                placeholder="Repetir contraseña"
                                className={`${poppins.className} max-x-xs`}
                                value={formik.values.repassword}
                            />
                            {formik.errors.repassword && formik.touched.repassword && (
                                <Alert message={formik.errors.repassword} />
                            )}
                            <Input
                                isRequired
                                type="number"
                                name="te"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                label="Telefóno/Celular"
                                className={`${poppins.className} max-x-xs`}
                                value={formik.values.te}
                            />
                            {formik.errors.te && formik.touched.te && <Alert message={formik.errors.te} />}
                            <Select
                                label="Rol"
                                placeholder="Rol"
                                name="roles"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                className={`${poppins.className} max-x-xs`}
                                value={formik.values.roles}
                            >
                                {roles?.map((rol) => (
                                    <SelectItem key={rol.id} value={rol.id}>
                                        {rol.value}
                                    </SelectItem>
                                ))}
                            </Select>
                            {formik.errors.roles && formik.touched.roles && <Alert message={formik.errors.roles} />}
                            {formik.values.roles[0] === '5' && (
                                <Select
                                    label="Area"
                                    placeholder="Area"
                                    name="areas"
                                    onChange={(e) => formik.handleChange(e)}
                                    onBlur={(e) => formik.handleBlur(e)}
                                    className={`${poppins.className} max-x-xs`}
                                    value={formik.values.areas}
                                >
                                    <SelectItem key={0} value={''}>
                                        Sin Area
                                    </SelectItem>
                                    {isLoading ? (
                                        <SelectItem key={0} value={''}>
                                            Cargando...
                                        </SelectItem>
                                    ) : (
                                        areaList?.map((area: { id: number; name: string }) => (
                                            <SelectItem key={area.id} value={area.id}>
                                                {area.name}
                                            </SelectItem>
                                        ))
                                    )}
                                </Select>
                            )}
                            {formik.errors.areas && formik.touched.areas && <Alert message={formik.errors.areas} />}
                            <Checkbox
                                isRequired
                                name="acceptTerms"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                className={`${poppins.className}`}
                            >
                                Acepto los Terminos y Condiciones
                            </Checkbox>
                            {formik.errors.acceptTerms && formik.touched.acceptTerms && (
                                <Alert message={formik.errors.acceptTerms} />
                            )}
                            <Button type="submit" color="primary" size="md" className={`${poppins.className}`}>
                                Registrarse
                            </Button>
                            <Link href="/auth/signin" className={`${poppins.className} text-xs`}>
                                ¿Ya tienes una cuenta? Inicia Sesión
                            </Link>
                        </form>
                    </div>
                </article>
            </section>
        </>
    );
};

export default SignUp;
