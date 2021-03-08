import axios from 'axios';


const http = axios.create({
    baseURL: "http//localhost:8080"
});

export default {
    list() {
        return http.get(`/restaurants`)
    },
    getRestaurants(zip) {
        return http.get(`/restaurants/${zip}`);
    },
    getTheRestaurant(id) {
        return http.get(`/restaurants/${id}`);
    }
}