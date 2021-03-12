<template>
  <main>
    <section class="left">
      <!-- Search Bar -->
      <div class="search-banner">
        <h1 class="search-heading">Where are we partying?</h1>
        <form class="location-search" v-on:submit.prevent>
          <div>
            <label for="searchLocate">Location:</label><br />
            <input
              id="searchLocate"
              v-model.lazy="location"
              type="text"
              placeholder="Enter a Zipcode or Location"
            /><br />
          </div>

          <div class="radius">
            <label for="selectRadius">Radius:</label><br />
            <select name="selectRadius" id="selectRadius" v-model="radius">
              <option value="8050">5 miles</option>
              <option value="16100">10 miles</option>
              <option value="24200">15 miles</option>
              <option value="40000">25 miles</option>
            </select>
          </div>
          <button class="find-food" v-on:click="searchByLocation()" focused>
            Find Food
          </button>
        </form>
      </div>

      <section>
        <!-- Categories -->
        <div class="cat-filter">
          <h1 class="search-heading">Filter Categories</h1>
          <p>(Choose your cravings)</p>
          <button class="find-food" v-on:click="searchByLocation()" focused>
            Update My Search
          </button>
          <label
            class="container"
            v-for="category in availCategories"
            v-bind:key="category.categoryId"
            >{{ category.displayName }}
            <input
              type="checkbox"
              checked="checked"
              v-bind:id="category.categoryId"
              v-bind:value="category.categoryId"
              v-model.number="categoriesSelected"
            />
            <span class="checkmark"></span>
          </label>
        </div>
      </section>
    </section>

    <section class="right">
      <div v-if="location === ''">
        <h1 class="loading-text">
          Let's Find You Some Grub <br />
          Enter a Location
        </h1>
      </div>
      <div class="restaurant-list">
        <div class="loading-gif" v-if="isLoading">
          <img src="../assets/loading.gif" />
        </div>

        <div v-if="!isLoading" class="result-list">
          <h1 class="title">
            Here are the restaurants we found near {{ location }}
          </h1>

          <restaurant-card
            v-for="restaurant in restaurants"
            v-bind:key="restaurant.id"
            v-bind:restaurant="restaurant"
            class="card"
          />
        </div>
      </div>
    </section>
  </main>

  <!-- This passes the restaurant[] and isLoading as a prop to restaurant list -->

  <!-- Category Dropdown -->
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
      location: "",
      invalidLocation: false,
      isLoading: true,
      availCategories: [],
      radius: "8050",
      categoriesSelected: [],
      noneFound: false,
    };
  },
  created() {
    this.getCategories();
    if (this.$store.state.restaurants.length !== 0) {
      this.restaurants = this.$store.state.restaurants;
      this.location = this.$store.state.searchLocation;
      this.radius = this.$store.state.radius;
      this.isLoading = false;
    }
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
      this.$store.commit("SET_SEARCH_LOCATION", this.location);
      this.$store.commit("SET_SEARCH_RADIUS", this.radius);

      RestaurantService.getRestaurants(
        this.location,
        this.radius,
        this.categoriesSelected.toString()
      )
        .then((response) => {
          this.restaurants = response.data;
          this.$store.commit("SET_RESTAURANT_LIST", this.restaurants);

          this.restaurants.sort(function (a, b) {
            return a.distance - b.distance;
          });
          this.restaurants = this.restaurants.filter( restaurant => { 
            if(this.radius >= restaurant.distance) {return restaurant}
          } );


          // this controls loading gif
          if (this.restaurants.length === 0) {
            this.noneFound = true;
            this.isLoading = true;
          } else {
            this.isLoading = false;
          }
        })

        .catch((error) => {
          const response = error.response;

          if (response.status === 401 && response.status === 500) {
            this.noneFound = true;
          }
        });

      //Display a message if no restaurants are returned
    },
  },
};
</script>
<style scoped>
main {
  display: flex;
  margin-top: 3em;
}

.left {
  display: flex;
  flex-direction: column;
  padding-left: 4em;
  border-right: 2px;
  border-color: black;
}
.right {
  margin-top: 2em;
  display: flex;
  width: 80vw;
  flex-direction: column;
}

section {
  display: flex;
}

label {
  font-weight: bold;
}

.location-search {
  display: flex;
  flex-direction: column;
}

.search-heading {
  font-weight: bold;
  font-size: 1.5em;
  margin-bottom: 1em;
}

.loading-text {
  font-weight: bold;
  font-size: 1.5em;
  padding-top: 5em;
  text-align: center;
  margin-bottom: -100em;
}
.search-bar {
  background-color: #fdf2f2;
}

.radius {
  margin-top: 1em;
}

select {
  width: 100%;
  height: 40px;
  border-radius: 3px;
  font-size: 1em;
  padding-left: 0.5em;
  font-weight: 600;
}

input {
  width: 100%;
  height: 40px;
  border-radius: 3px;
  padding-left: 0.5em;
  font-size: 1em;
  font-weight: 600;
}

.cat-filter .find-food {
  margin-bottom: 2em;
  background-color: #81974e;
}
.find-food {
  background-color: #dc6b67;
  border: none;
  color: white;
  padding: 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 5px;
  width: 100%;
  margin-top: 30px;
  margin-top: 2em;
  font-weight: bold;
}

.find-food:hover {
  background-color: #f7a09d;
}

.cat-filter .find-food:hover {
  background-color: #a5c064;
}

/*  Check boxes start here -----------------------------*/

.container {
  display: flex;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 1em;
  font-weight: bold;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

/* Create a custom checkbox ------------------- */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  border-style: solid;
  border-width: 0.1em;
  border-color: #1e1e32;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.container input:checked ~ .checkmark {
  background-color: #dc6b67;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the checkmark when checked */
.container input:checked ~ .checkmark:after {
  display: block;
}

/* Style the checkmark/indicator */
.container .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}

.cat-filter {
  padding-top: 4em;
  display: inline-block;
}

.cat-filter p {
  margin-top: -1.5em;
  font-weight: 500;
  margin-bottom: -1em;
}

/*  rest list */

.loading-gif {
  display: flex;
  justify-content: center;
  height: 80vh;
  margin-top: -1em;
  margin-left: 7em;
}

.loading-gif img {
  width: 400px;
  height: 400px;
  object-fit: contain;
  align-self: center;
}
.restaurant-list {
  width: 70vw;
  margin-left: 2em;
}
.result-list {
  padding-left: 10em;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.card {
  width: 75vw;
  margin-top: 25px;
  margin-bottom: 25px;
  padding: 1em;
}
</style>