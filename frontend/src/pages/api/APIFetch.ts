import APIrequest from './axiosConfig';

export const login = async () => {
    const body = {
        email: 'sonyablade@gmail.com',
        password: 'Sonia@blade1',
    };

    return APIrequest.post('https://findatrader-jebius.koyeb.app/api/v1/auth/login', body);
};

export const clientRequestList = async () => {
    return APIrequest.get('https://findatrader-jebius.koyeb.app/api/v1/client/request/list/paged?page=0&size=4');
};

export const providerRequestList = async (endpoint) => {
    return APIrequest.get(endpoint);
};

export const userDetails = async () => {
    return APIrequest.get('https://findatrader-jebius.koyeb.app/api/v1/client/details');
};
