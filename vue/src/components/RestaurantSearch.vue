<template>
  <div class ="search-container">
    
    <div class="loading-gif" v-if="isLoading">
      <img src="../assets/loading.gif" />
    </div>

    <div id="location-container" class="text-center" v-if="!isLoading">
      <form class="location-search" v-on:submit.prevent="searchByLocation">
        <h1 class="h3 mb-3 font-weight-normal">Where are we partying?</h1>
        <div class="alert alert-danger" role="alert" v-if="invalidLocation">
          Invalid location parameters, search with address or zip code
        </div>

        <!-- Loading GIf -->

        <b-field label="Location">
          <b-input type="text" class="seachbox" v-model="location" />
        </b-field>
        <b-button  v-on:click="searchByLocation()" focused>
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
  name: "restaurant-search",
  components: { RestaurantCard },
  
  data() {
    return {
      restaurants: [],
      isLoading: true,
      location: "",
      invalidLocation: false,
    };
  },
  methods: {
    viewRestaurant(id) {
      this.$router.push(`restaurants/${id}`);
    },
    searchByLocation() {
      RestaurantService.getRestaurants(this.location)
        .then((response) => {
          this.restaurants = response.data;
          this.isLoading = false;
          //this.$router.push("/") This is where we populate restaurant cards
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidLocation = true;
          }
        });
      console.log(this.restaurants);
      this.isLoading = false;
    },
  },
};
</script>
<style scoped>

.search-container{
  background-color:#fdf2f2;
  height:90vh;
}

.loading-gif{
  text-align:center;

}



</style>