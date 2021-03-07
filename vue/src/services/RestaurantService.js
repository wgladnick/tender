import axios from 'axios';

const http = axios.create({
    baseURL: "http//localhost:3000"
});

const RESTAURANT_PATH = "/restaurants";

export default {
    getRestaurants() {
        return http.get(RESTAURANT_PATH);
    }
}