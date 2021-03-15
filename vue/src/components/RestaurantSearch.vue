Restaurant Search

<template>

<!-- Side Nav -->
  <main class="body">
      <button class="nav-butt" v-on:click="isMenuOpen = false"> Hide List </button>

<div id="mySidenav" class="sidenav" v-bind:style="{width: isMenuOpen ? '400px' : '0px'}">


  
  <ul>
    <li v-for="rest in selectedRestauraunts" v-bind:key="rest.id">
      <h1> {{rest.name}} </h1><button v-on:click="updateList(rest)">x</button>
      </li>
    </ul>
</div>

<!-- Use any element to open the sidenav -->

<span class="open-button"  v-on:click="isMenuOpen=true"><button>OPEN</button></span>
<!-- Add all page content inside this div if you want the side nav to push page content to the right (not used if you only want the sidenav to sit on top of the page -->

    <!-- Left Panel -->
    <section class="left" v-if="isShowingResults === true">
      <!-- Search Bar - Left Panel -->
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

      <!-- Categories -->
      <div>
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
      </div>
    </section>
    <!--Categories End -->

    <!-- Initial Search -->
    <section class="login-search">
      <div class="initial-search" v-if="isInitialSearch === true">
        <div class="loading-gif">
          <img src="../assets/loading.gif" />
        </div>
        <div v-if="location === ''">
        
          <h1 class="loading-text">Let's Find You Some Grub</h1>
        </div>

        <div class="search-main" >
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
        </div>
      </div>
    </section>
    <!-- Inital Search Ends -->

<div id="main" v-bind:style="{ 'margin-left': isMenuOpen ? '-175px' : '0px' }">

    <!--Restaurant List Body -->
    <section class="middle">
      <span class="restaurant-list">
        <div v-bind:class="{ isShowingResults: !isShowingResults }" class="loading-gif" v-if="isLoading">
          <img src="../assets/loading.gif" />
        </div>

        <div v-if="hasResults" class="result-list">
         
          <h1 class="title">
            Here are the restaurants we found near {{ updatedLocation }}
          </h1>

            
          <restaurant-card
            v-for="restaurant in restaurants"
            v-bind:key="restaurant.id"
            v-bind:restaurant="restaurant"
            class="card" 
            @update-list="updateList"/>
    
       
    

          <!-- Restaurant List Body Ends -->

          <div class="right"></div>

         
        </div>
      </span>
    </section>
    </div>
  </main>

  <!-- This passes the restaurant[] and isLoading as a prop to restaurant list -->
</template>
<script>
import RestaurantService from "../services/RestaurantService";
import RestaurantCard from "../components/RestaurantCard";



export default {
  name: "restaurant-search",
  components: {
    RestaurantCard,
   
  },

  data() {
    return {
      isMenuOpen: false,
      restaurants: [],
      location: "",
      invalidLocation: false,
      isLoading: false,
      isInitialSearch: true,
      isShowingResults: false,
      availCategories: [],
      radius: "8050",
      categoriesSelected: [],
      noneFound: false,
      updatedLocation: "",
      selectedRestauraunts:[],
      user:{}
    };
  },
  created() {
    this.getCategories();
    if (this.$store.state.restaurants.length !== 0) {
      this.restaurants = this.$store.state.restaurants;
      this.location = this.$store.state.searchLocation;
      this.radius = this.$store.state.radius;
      this.isLoading = false;
      this.isShowingResults = true;
      this.isInitialSearch = false;
      this.updatedLocation = this.location;
    }

    this.user = this.$store.state.user;
  },

  

  computed: {
    hasResults() {
      return this.isShowingResults === true && this.isLoading === false;
    },
  },

  methods: {

  updateList(rest){
    const index = this.selectedRestauraunts.indexOf(rest)
    if(!this.selectedRestauraunts.includes(rest)){
    this.selectedRestauraunts.push(rest);
    }else{
      this.selectedRestauraunts.splice(index,1);

    }
  },

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
      this.isInitialSearch = false;

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
          this.restaurants = this.restaurants.filter((restaurant) => {
            if (this.radius >= restaurant.distance) {
              return restaurant;
            }
          });

          // this controls loading gif
          if (this.restaurants.length === 0) {
            this.noneFound = true;
            this.isLoading = true;
          } else {
            this.updatedLocation = this.location;
            this.isLoading = false;
            this.isShowingResults = true;
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

.open-button{
  position:fixed;
  top:0;
  right:0;
  z-index:3;
  height:50px;
  
}
.nav-butt{
  height:50px;
  position:fixed;
  right:0;
  z-index:3;


}

.sidenav {
  height: 100vh; /* 100% Full-height */
  width: 0px; /* 0 width - change this with JavaScript */
  position: fixed; /* Stay in place */
  z-index: 2; /* Stay on top */
  bottom: 0; /* Stay at the top */
  right: 0;
  background-color:#e08f8c; /* Black*/
  overflow-x: hidden; /* Disable horizontal scroll */
  padding-top: 60px; /* Place content 60px from the top */
  transition: 0.5s; /* 0.5 second transition effect to slide in the sidenav */
}

/* The navigation menu links */
.sidenav a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 25px;
  color: #000000;
  display: block;
  transition: 0.3s;
}

/* When you mouse over the navigation links, change their color */
.sidenav a:hover {
  color: #f1f1f1;
}

/* Position and style the close button (top right corner) */
.sidenav .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

/* Style page content - use this if you want to push the page content to the right when you open the side navigation */
#main {
  transition: margin-left .5s;
  padding: 20px;
}

/* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}

.isShowingResults{
  margin-left:10em;
  margin-top:-5em;
 
}

.body {
  display: flex;
  flex-direction: row;
  margin-top: 5em;
  height: 100%;
}

.left {
  display: flex;
  flex-direction: column;
  padding-left: 4em;
  border-right: 2px;
  border-color: black;
  width: 20vw;
}
.middle {
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
/* Home search */
.search-main {
  width: 30%;
  align-self: center;
  margin-top: 3em;
}

.initial-search {
  display: flex;
  flex-direction: column;
  width: 100vw;
  height: 80vh;
  margin-top: 0em;
}
/* Home Search Ends Here */

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
  padding-top: 0;
  text-align: center;
  margin-bottom: -100em;
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

/* Update My Search Button */
.cat-filter .find-food {
  margin-bottom: 2em;
  background-color: #81974e;
}

.cat-filter .find-food:hover {
  background-color: #a5c064;
}

/* Find Food Button */
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

/* Checkboxes end here */

/*  Restaurant List */

.loading-gif {
  display: flex;
  justify-content: center;
  height: 20vh;
}

.loading-gif img {
  width: 400px;
  max-height: 400px;
  object-fit: contain;
  align-self: center;
  margin-top: -em;
}

.restaurant-list .loading-gif {
  width: 100%;
  height: 75vh;
}
.restaurant-list .loading-gif img {
  padding: 0;
}

.restaurant-list {
  display: flex;
  flex-direction: column;
  width: 70vw;
  margin-left: 2em;
}
.result-list {
 
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.card {
  width: 50vw;
  margin-top: 25px;
  margin-bottom: 25px;
  padding: 1em;
}
</style>