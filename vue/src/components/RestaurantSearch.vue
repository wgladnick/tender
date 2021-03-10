<template>
  <div id="search-bar" class="text-center">
    <form class="location-search" v-on:submit.prevent="searchByLocation">
      <h1 class="h3 mb-3 font-weight-normal">Where are we partying?</h1>

      <div class="alert alert-danger" role="alert" v-if="invalidLocation">
        Invalid location parameters, search with address or zip code
      </div>

      <b-field label="Location">
        <b-input type="text" class="searchbox" v-model="location" />
      </b-field>

      <b-button v-on:click="searchByLocation()" focused> Search </b-button>

      <!-- This passes the restaurant[] and isLoading as a prop to restaurant list -->
      <restaurant-list v-bind:restaurants="restaurants" />
      <restaurant-list v-bind:isLoading="isLoading" />
    </form>
  </div>
</template>
<script>
import RestaurantService from "../services/RestaurantService";
import RestaurantList from "./RestaurantList.vue";

export default {
  name: "restaurant-search",
  components: { RestaurantList },

  data() {
    return {
      restaurants: [],
      location: "",
      invalidLocation: false,
      isLoading: true
    };
  },
  computed: {},
  methods: {
    viewRestaurant(id) {
      this.$router.push(`restaurants/${id}`);
    },
    searchByLocation() {
      this.restaurants = [];
      this.isLoading = true;
      RestaurantService.getRestaurants(this.location)
        .then((response) => {
          this.restaurants = response.data;

         // this controls loading gif 
          if (this.restaurants.length === 0) {
            this.isLoading = true;
          } else {
            this.isLoading = false;
          }

          
        })

        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidLocation = true;
          }
        });
      console.log(this.restaurants);
      //Display a message if no restaurants are returned
    },
  },
};
</script>
<style scoped>
.search-bar {
  background-color: #fdf2f2;
}
</style>