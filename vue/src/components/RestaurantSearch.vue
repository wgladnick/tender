Restaurant Search

<template>
  <!-- Side Nav -->
  <main class="body">
    <div
      id="mySidenav"
      class="sidenav"
      v-bind:style="{ width: isMenuOpen ? '500px' : '0px' }"
    >
      <!-- Side Nav -->

      <span>
        <h1>Create My Invite</h1>
        <p>
          Select your restaurant choices to put to a vote, add some guests and
          let's have a party!
        </p>

        <!-- Sidenav Restaurant List -->
        <ul>
          <li
            class="list-item"
            v-for="rest in restaurantChoices"
            v-bind:key="rest.id"
          >
            <!-- Delete Restaurant From List -->
            <button
              @click="updateList(rest)"
              class="delete"
              id="delete-invitee"
            />
            <h1>{{ rest.name }}</h1>
          </li>
        </ul>

        <!-- Dinner Card -->
        <dinner-card />
      </span>
      <!-- Side Nave ends here -->

      <div class="cancel">
        <b-button type="is-primary" v-on:click="toggleSideMenu()">
          Cancel</b-button
        >
      </div>
    </div>

    <!-- Left Panel -->
    <section class="left" v-if="isShowingResults === true">
      <!-- Search Bar - Left Panel -->

      <div class="search-banner">
        <h1>Where are we partying?</h1>
        <form v-on:submit.prevent>
          <b-field label="Location">
            <b-input
              v-model="location"
              placeholder="Enter a Zipcode or Location"
              expanded
              required
            ></b-input>
          </b-field>

          <b-field label="Radius">
            <b-select placeholder="Enter a radius" v-model="radius" expanded>
              <option value="8050">5 miles</option>
              <option value="16100">10 miles</option>
              <option value="24200">15 miles</option>
              <option value="40000">25 miles</option>
            </b-select>
          </b-field>

          <b-button expanded type="is-primary" v-on:click="searchByLocation()"
            >Find Food</b-button
          >
        </form>
      </div>

      <!-- Categories -->
      <div>
        <div>
          <h1>Filter Categories</h1>
          <p>(Choose your cravings)</p>
          <b-button type="is-primary" v-on:click="searchByLocation()" expanded
            >Update My Search</b-button
          >

          <label
            class="c-container"
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

    <div class="home-search-container" v-if="isInitialSearch">
      <div class="home-search" >
        <div class="loading-gif">
          <img src="../assets/loading.gif" />
        </div>

        <h2 class="is-size-3 has-text-weight-semibold has-text-centered">
          Let's Find You Some Grub
        </h2>
        <br />
        <span v-if="isError">{{ errorMsg }}</span>

        <b-input
          required
          v-model="location"
          placeholder="Enter a Zipcode or Location"
        >
        </b-input>
        <br />

        <b-select placeholder="Enter a radius" v-model="radius" expanded>
          <option>Enter a radius</option>
          <option value="8050">5 miles</option>
          <option value="16100">10 miles</option>
          <option value="24200">15 miles</option>
          <option value="40000">25 miles</option>
        </b-select>
        <br />

        <b-button
          submit
          expanded
          type="is-primary"
          v-on:click="searchByLocation()"
          >Find Food</b-button
        >
      </div>
    </div>
    <!-- Inital Search Ends -->

    <div
      id="main"
      v-if="!isInitialSearch"
      v-bind:style="{ 'margin-left': isMenuOpen ? '-225px' : '0px' }"
    >
      <!--Restaurant List Body -->
      <section class="middle">
        <span class="restaurant-list">
          <div
            v-bind:class="{ isShowingResults: !isShowingResults }"
            class="loading-gif"
            v-if="isLoading"
          >
            <img src="../assets/loading.gif" />
          </div>

          <div v-if="hasResults" class="result-list">
            <h1>
              Here are the restaurants we found near {{ updatedLocation }}
            </h1>
            <div>
              <b-button
                type="is-primary"
                v-show="!this.isMenuOpen"
                v-on:click="toggleSideMenu()"
                >Invite Some Friends Out For Food</b-button
              >
            </div>
            <restaurant-card
              v-for="restaurant in restaurants"
              v-bind:key="restaurant.id"
              v-bind:restaurant="restaurant"
              class="card"
              @update-list="updateList"
            />

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
import DinnerCard from "../components/DinnerCard";
import InviteService from "../services/InviteService";

export default {
  name: "restaurant-search",
  components: {
    RestaurantCard,
    DinnerCard,
  },

  data() {
    return {
      createdInvite: {},
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
      restaurantChoices: [],
      errorMsg: "",
      isError: false,
      invitation: {
        location: "",
        radius: "",
      },
      user: {},
    };
  },
  created() {
     //loads available categories and sets preferred categories
    this.getCategories();
    this.categoriesSelected = this.$store.state.user.userDetails.activeCategoryId;


    //loads $store information
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
    this.$store.commit("SET_TOGGLE_STATUS");
    //this.isMenuOpen = this.$store.state.sideMenuToggle;

    this.$store.state.invitation.restaurantChoices = [];
  },

  computed: {
    hasResults() {
      return this.isShowingResults === true && this.isLoading === false;
    },
  },

  methods: {
    sendInvite() {
      InviteService.sendInvite(this.createdInvite)
        .then((response) => {
          this.$store.commit("SET_CREATED_INVITE", response.data);
          this.createdInvite = {};
          this.$router.push("/confirmation");
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401 && response.status === 500) {
            this.errorMsg =
              "Sorry there was an error creating your invitation, please try again.";
          }
        });
    },

    toggleSideMenu() {
      this.$store.commit("SET_TOGGLE_STATUS");
      this.isMenuOpen = !this.$store.state.sideMenuToggle;
    },

    updateList(rest) {
      const index = this.restaurantChoices.indexOf(rest);
      if (!this.restaurantChoices.includes(rest)) {
        this.restaurantChoices.push(rest);
      } else {
        this.restaurantChoices.splice(index, 1);
        this.$store.state.invitation.restaurantChoices.splice(index, 1);
        console.log(this.$store.state.invitation.restaurantChoices);
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
      if (this.location === "") {
        this.isError = true;
        this.errorMsg = "Don't forget your location :)";
      } else {
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

            if (response.status === 401 || response.status === 500) {
              this.noneFound = true;
              this.errorMsg = "Please enter valid search parameters";
            }
          });
      }
      //Display a message if no restaurants are returned
    },
  },
};
</script>
<style scoped>

.body {
  display: flex;
  flex-direction: row;
} 

.home-search-container {
  display: flex;
  justify-content: center;
width:100%;
}
.home-search {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.list-item {
  display: flex;
}

.list-item {
  padding-left: 1.5em;
  font-size: 1.5em;
  font-weight: 800;
}


.left {
  display: flex;
  flex-direction: column;
  padding-left: 3em;
  margin-top: 5em;
  width:20%;
}


/*  Restaurant List */

.loading-gif {
  display: flex;
  justify-content: center;
}

.loading-gif img {
  width: 400px;
  max-height: 400px;
  object-fit: contain;
  align-self: center;
  margin-bottom: -3em;
}

.restaurant-list .loading-gif {
  width: 100%;
  height: 75vh;
}

.result-list {
  display: flex;
  flex-direction: column;

}

.card {
  margin-top: 25px;
  margin-bottom: 25px;
  padding: 1em;
}

/* Restaurant List Ends Here */

/* Side nav starts here  */
.sidenav {
  height: 88.5vh; /* 100% Full-height */
  width: 0px; /* 0 width - change this with JavaScript */
  position: fixed; /* Stay in place */
  z-index: 2; /* Stay on top */
  bottom: 0; /* Stay at the top */
  right: 0;
  background-color: #f8c5c2; /* Black*/
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

/* Style page content - use this if you want to push the page content to the right when you open the side navigation */
#main {
  transition: margin-left 0.5s;
  padding: 20px;
  width:80%;
}

/* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
@media screen and (max-height: 450px) {
  .sidenav {
    padding-top: 15px;
  }
  .sidenav a {
    font-size: 18px;
  }
}
/* Side nav ends here */

/*  Check boxes start here -----------------------------*/



.c-container {
  display: flex;
  position: relative;
  padding-left: 30px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 1em;
  font-weight: bold;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.c-container input {
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
.c-container:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.c-container input:checked ~ .checkmark {
  background-color: #dc6b67;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the checkmark when checked */
.c-container input:checked ~ .checkmark:after {
  display: block;
}

/* Style the checkmark/indicator */
.c-container .checkmark:after {
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
</style>