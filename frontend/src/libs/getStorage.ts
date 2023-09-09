type sessionStorage = {
    jwt: string;
    user: {
        userName: string;
        lastName: string;
        roles: string[];
    };
};

const getSession = localStorage.getItem('sessionStorage');
const sessionStorage: sessionStorage = JSON.parse(getSession);

const getStorageToken = () => {
    const token = sessionStorage.jwt;

    return token;
};

const getStorageUser = () => {
    const user = sessionStorage.user;

    return user;
};

const isAuthStorage = () => Object.keys(sessionStorage).length === 0;

export { getStorageToken, getStorageUser, isAuthStorage };
