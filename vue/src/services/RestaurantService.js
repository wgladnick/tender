import axios from 'axios';




export default {
    list() {
        return axios.get('/restaurant');
    },
    getRestaurants(location) {
        return axios.get('/restaurant/', {
            params: {
                address: location,
                radius: 1000
            }
        });
    },
    getTheRestaurant(id) {
        return axios.get(`/restaurant/${id}`);
    },
    getRestaurantByCategory(category) {
        return axios.get('/category', {
            params: {
                address: location,
                radius: 1000,
                category: category
            }
        })
    }
}