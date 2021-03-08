import axios from 'axios';
import { get } from 'core-js/core/dict';

const http = axios.create({
    baseURL: "http//localhost:3000"
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