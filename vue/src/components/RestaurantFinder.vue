<template>
    <div v-for="restaurant in restaurants" v-bind:key="restaurant.id" class="restaurant">
    router-link v-bind:to="name: 'Restaurant'"
    </div>
</template>
<script>
import RestaurantService from "../services/RestaurantService";
export default {
    name: 'restaurant-finder',
    data() {
        return {
        restaurants: [],
        isLoading: true
        }
    },
    methods: {
        viewRestaurant(id) {
            this.$router.push(`/${id}`);
        }
    },
    created() {
        RestaurantService.list().then((response) => {
            this.restaurants = response.data;
        })
    }
}
</script>
<style scoped>

</style>