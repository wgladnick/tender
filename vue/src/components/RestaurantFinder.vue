<template>
  <div>
    <div id="location-container" class="text-center">
      <form class="location-search" v-on:submit.prevent="searchByLocation">
        <h1 class="h3 mb-3 font-weight-normal">Where are we partying?</h1>
        <b-field label="Location">
          <b-input type="text" class="seachbox" v-model="location" />
        </b-field>
        <b-button type="submit" v-on:click="searchByLocation()" focused>
          Search
        </b-button>
      </form>
      <div v-if="!isLoading">
        <restaurant-card
          v-for="restaurant in restaurants"
          v-bind:key="restaurant.id"
          v-bind:restaurant="restaurant"
        />
      </div>
    </div>
  </div>
</template>
<script>
import RestaurantService from "../services/RestaurantService";
import RestaurantCard from "../components/RestaurantCard";
export default {
  name: "restaurant-finder",
  components: { RestaurantCard },
  data() {
    return {
      restaurants: [],
      isLoading: true,
      location: "",
    };
  },
  methods: {
    viewRestaurant(id) {
      this.$router.push(`restaurants/${id}`);
    },
    searchByLocation() {
      RestaurantService.getRestaurants(this.location).then((response) => {
        this.restaurants = response.data;
      });
      console.log(this.restaurants);
      this.isLoading = false;
    },
  },
};
</script>
<style scoped>
</style>