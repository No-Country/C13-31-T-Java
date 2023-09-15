import { useEffect } from 'react';
import { type NextPage } from 'next';
import Image from 'next/image';
import Head from 'next/head';
import Link from 'next/link';
import { useRouter } from 'next/router';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { signIn, useSession } from 'next-auth/react';
import { Input, Checkbox, Button } from '@nextui-org/react';
import { Poppins } from 'next/font/google';
import toast, { Toaster } from 'react-hot-toast';
import Alert from '@components/Alert';
import Logo from '../../../assets/images/findatrader.png';

const poppins = Poppins({ weight: '400', subsets: ['latin-ext'] });

const SignIn: NextPage = () => {
    const router = useRouter();

    const { data: session } = useSession();

    useEffect(() => {
        setTimeout(() => {
            if (session) {
                // APIrequest.defaults.headers.common['Authorization'] = 'Bearer ' + session.token.jwt;
                if (session.token.user.roles[0] == 'Client') router.push('/client/tasks');
                if (session.token.user.roles[0] == 'Provider') router.push('/provider/tasks');
            }
        }, 2000);
    }, [session]);

    const formik = useFormik({
        initialValues: {
            email: '',
            password: '',
        },
        validationSchema: Yup.object({
            email: Yup.string().email('Email invalido').required('Requerido'),
            password: Yup.string().required('Requerido'),
        }),
        onSubmit: async (values) => {
            const loadingToast = toast.loading('Iniciando sesión...');
            const { email, password } = values;
            try {
                const result = await signIn('credentials', {
                    email,
                    password,
                    redirect: false,
                });

                if (result.ok) {
                    toast.dismiss(loadingToast);
                    toast.success('Sesión iniciada correctamente');
                }
            } catch (error) {
                toast.dismiss(loadingToast);
                toast.error('Error ' + error);
            }
        },
    });

    return (
        <>
            <Head>
                <title>FaT - Inicia sesion</title>
            </Head>
            <section className="w-full h-screen">
                <Toaster />
                <article className="flex justify-center items-center w-full h-full">
                    <div>
                        <div className="flex flex-row justify-between items-center">
                            <h1 className={`${poppins.className}`}>Inicia sesion</h1>
                            <Image src={Logo} alt="fat logo" width={250} />
                        </div>
                        <form
                            className="flex flex-col justify-between items-center p-5 w-[25rem] h-[20rem]"
                            onSubmit={(e) => {
                                e.preventDefault();
                                formik.handleSubmit(e);
                            }}
                        >
                            <Input
                                isRequired
                                type="email"
                                label="Email"
                                id="email"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                placeholder="Correo electronico"
                                className={`${poppins.className} max-x-xs`}
                                value={formik.values.email}
                            />
                            {formik.errors.email && formik.touched.email && <Alert message={formik.errors.email} />}
                            <Input
                                isRequired
                                type="password"
                                label="Contraseña"
                                id="password"
                                onChange={(e) => formik.handleChange(e)}
                                onBlur={(e) => formik.handleBlur(e)}
                                placeholder="Contraseña"
                                className={`${poppins.className} max-x-xs`}
                                value={formik.values.password}
                            />
                            {formik.errors.password && formik.touched.password && (
                                <Alert message={formik.errors.password} />
                            )}
                            <Checkbox className={`${poppins.className}`}>Recuerdame</Checkbox>
                            <Button color="primary" size="md" className={`${poppins.className}`} type="submit">
                                Iniciar sesion
                            </Button>
                        </form>
                        <div className="flex flex-col justify-center items-center">
                            <Link href="/auth/signup" className={`${poppins.className} text-xs`}>
                                ¿No tienes una cuenta? Registrate
                            </Link>
                            <Link href="/auth/forgot-password" className={`${poppins.className} text-xs`}>
                                ¿Olvidaste tu contaseña?
                            </Link>
                        </div>
                    </div>
                </article>
            </section>
        </>
    );
};

export default SignIn;
