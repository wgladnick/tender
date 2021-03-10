import axios from 'axios';




export default {
    list() {
        return axios.get('/restaurant');
    },
<<<<<<< HEAD
    getRestaurants(location, categories) {
=======
    getRestaurants(location,radius,categories) {
>>>>>>> 3b87dd5cbd3f8232fa805273220436589a644d19
        return axios.get('/restaurant/', {
            params: {
                address: location,
                radius: radius,
                categories: categories 
            }
        });
    },
    getTheRestaurant(id) {
        return axios.get(`/restaurant/${id}`);
<<<<<<< HEAD
=======
    },
    getRestaurantByCategory(category) {
        return axios.get('/category', {
            params: {
                address: location,
                radius: 1000,
                category: category
            }
        })
    },

    getAvailableCategories(){
        return axios.get('/categories');
>>>>>>> 3b87dd5cbd3f8232fa805273220436589a644d19
    }
}