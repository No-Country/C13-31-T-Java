import axios from 'axios';

const httpClient = axios.create({
    baseURL: 'https://findatrader-jebius.koyeb.app/api/v1',
    headers: {
        'Content-Type': 'application/json',
    },
});

export default httpClient;
