import axios from 'axios';


const http = axios.create({
    baseURL: "http//localhost:8080"
});

export default {
    list() {
        return http.get(`/restaurants`)
    },
    getRestaurants(location) {
        return http.get(`/restaurants/${location}`);
    },
    getTheRestaurant(id) {
        return http.get(`/restaurants/${id}`);
    }
}