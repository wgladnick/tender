import axios from 'axios';




export default {
    list() {
        return axios.get('/restaurant');
    },
    getRestaurants(location, categories) {
        return axios.get('/restaurant/', {
            params: {
                address: location,
                radius: 1000
            }
        });
    },
    getTheRestaurant(id) {
        return axios.get(`/restaurant/${id}`);
    }
}