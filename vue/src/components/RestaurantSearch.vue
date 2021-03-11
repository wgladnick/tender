<template>
<main>
 <section class ="left">
   <!-- Search Bar -->
    <div class="search-banner">
      <h1 class="h3 mb-3 font-weight-normal">Where are we partying?</h1>
  <form class="location-search" v-on:submit.prevent>

    <div>
  <label for="fname">Location</label><br>
  <input v-model="location" type="text" id="fname" name="fname" placeholder="Enter a Zipcode or Location"><br>
  </div>

  <div>
  <label for="lname">Radius</label><br>
  <select name="radius" id="radius">
    <option value="5">5 miles </option>
    <option value="10">25 miles </option>
    <option value="15">15 miles </option>
    <option value="25">25 miles </option>
    </select>
    </div>
      <button v-on:click="searchByLocation()" focused> Find Food </button>
</form>
</div>

   
  <section>
  <!-- Categories -->
  <div class="cat-filter">
  <label class="container" v-for="category in availCategories" 
  v-bind:key="category.categoryId">{{category.displayName}}
  <input type="checkbox" checked="checked"
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
 <div class="restaurant-list">
    <div class="loading-gif" v-if="isLoading">
      <img src="../assets/loading.gif" />
    </div>

    <div v-if="!isLoading" class="result-list">
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
import RestaurantCard from "../components/RestaurantCard"


export default {
  name: "restaurant-search",
  components: { RestaurantCard},

  data() {

   return {
      restaurants: [],
      location: "",
      invalidLocation: false,
      isLoading: true,
      availCategories: [],
      radius:"",
      categoriesSelected:[]
   
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
      
      //Display a message if no restaurants are returned
    },
  },
};
</script>
<style scoped>

main{
  display:flex;
  margin-top:3em;
}

.left{
  display:flex;
  flex-direction:column;
    padding-left:4em;
}
.right{
  display:flex;
  width:80vw;

}

section{
  display:flex;
}

.container {
  display: flex;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 1.2em;
  font-weight:bold;
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
  border-width: .1em;
  border-color:#1e1e32;
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

.cat-filter{
  padding-top:4em;

  display: inline-block;
  }

.location-search{
  display:flex;
  flex-direction:column;
}
.search-bar {
  background-color: #fdf2f2;
}





/*  rest list */

.loading-gif {
  display: flex;
  justify-content: center;
  height: 80vh;
 
}

.loading-gif img {
  width: 400px;
  height: 400px;
  object-fit: contain;
   align-self: center;

}
.restaurant-list{
  width:70vw;
}
.result-list {
  padding-left:10em;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.card {
  width:75vw;
  margin-top: 25px;
  margin-bottom: 25px;
  padding: 1em;
}

</style>