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

        <ul class="items">
    <li v-for="category in availCategories" v-bind:key="category.categoryId" ><input type="checkbox"
              v-bind:id="category.categoryId"
              v-bind:value="category.categoryId"
              v-model.number="categoriesSelected"/>{{category.displayName}}</li>

  </ul>

      <!-- This passes the restaurant[] and isLoading as a prop to restaurant list -->
      <restaurant-list v-bind:restaurants="restaurants" />
      <restaurant-list v-bind:isLoading="isLoading" />
    </form>


<!-- Category Dropdown -->

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
      isLoading: true,
      availCategories: [],
      categoriesSelected: [],
      radius:"",
   
    };
  },
  created() {
    this.getCategories();
  },

  methods: {
    

    getCategories() {
      RestaurantService.getAvailableCategories().then((response) => {
        this.availCategories = response.data;
      });
    },

    viewRestaurant(id) {
      this.$router.push(`restaurants/${id}`);
    },
    searchByLocation() {
      this.restaurants = [];
      this.isLoading = true;
      RestaurantService.getRestaurants(this.location,this.radius,this.categoriesSelected.toString())
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

.dropdown-check-list {
  display: inline-block;
}

.dropdown-check-list .anchor {
  position: relative;
  cursor: pointer;
  display: inline-block;
  padding: 5px 50px 5px 10px;
  border: 1px solid #ccc;
}

.dropdown-check-list .anchor:after {
  position: absolute;
  content: "";
  border-left: 2px solid black;
  border-top: 2px solid black;
  padding: 5px;
  right: 10px;
  top: 20%;
  -moz-transform: rotate(-135deg);
  -ms-transform: rotate(-135deg);
  -o-transform: rotate(-135deg);
  -webkit-transform: rotate(-135deg);
  transform: rotate(-135deg);
}

.dropdown-check-list .anchor:active:after {
  right: 8px;
  top: 21%;
}

.dropdown-check-list ul.items {
  padding: 2px;
  display: none;
  margin: 0;
  border: 1px solid #ccc;
  border-top: none;
}

.dropdown-check-list ul.items li {
  list-style: none;
}

.dropdown-check-list.visible .anchor {
  color: #0094ff;
}

.dropdown-check-list.visible .items {
  display: block;
}
</style>