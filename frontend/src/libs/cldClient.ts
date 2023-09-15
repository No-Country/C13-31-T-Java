import axios from 'axios';

const cldClient = async (images: File[]) => {
    try {
        const formData = new FormData();
        const imagesUrls = await Promise.all(
            images.map(async (image) => {
                formData.append('file', image);
                formData.append('upload_preset', 'findatrader');

                const img = await axios({
                    baseURL: 'https://api.cloudinary.com/v1_1/findatrader/upload',
                    method: 'POST',
                    data: formData,
                });

                console.log(img.data.secure_url);

                if (img.data.secure_url) {
                    return img.data.secure_url;
                }
            }),
        );
        return imagesUrls.filter((url) => url !== undefined);
    } catch (error) {
        console.error('Error en cldClient:', error);
        return [];
    }
};

export default cldClient;
