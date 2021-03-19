<template>
<section class="tile is-ancestor">

   
    <div id="tile is-parent">
       
      <div class="tile is-child box is-7">

   
          <h1 class="h3 mb-3 font-weight normal">Edit Profile</h1>

          <div class="alert" role="alert" v-if="editProfileErrors">
            {{ editProfileErrorMsg }}
          </div>
          
          <b-field label="First Name">
            <b-input expanded v-model="user.firstName" required></b-input>
          </b-field>
          <b-field label="Last Name">
            <b-input v-model="user.lastName" required></b-input>
          </b-field>
          <b-field label="Email Address">
            <b-input v-model="user.email">
              <i class="fas fa-envelope"></i>
            </b-input>
          </b-field>
          <b-field label="Username">
            <b-input maxLength="15" v-model="user.username" required></b-input>
          </b-field>
          <b-field label="Address">
            <b-input v-model="user.userDetails.address"> </b-input>
          </b-field>
          <b-field label="City">
            <b-input v-model="user.userDetails.city"> </b-input>
          </b-field>
          <b-field label="State">
            <b-input v-model="user.userDetails.state"> </b-input>
          </b-field>
          <b-field label="Zip Code">
            <b-input v-model="user.userDetails.zip"></b-input>
          </b-field>
      
      </div>

</div>
<div class="tile is-parent is-vertical">

      <div class="tile is-child box">
        <br /><br />
        <h1 class="h3 mb-3 font-weight normal">Edit Favorite Categories</h1>

        <div
          
          v-for="category in this.foodCategories"
          v-bind:key="category.categoryId"
        >
        <b-field
            v-bind:id="category.categoryId"
            v-bind:value="category.categoryId"
            v-model.number="userCategories">
            <b-checkbox type="is-primary">{{ category.displayName }}</b-checkbox>
        </b-field>
        </div>


        </div>


         <div class="tile is-child">
    <button type="submit" v-on:click.prevent="editProfile">Save Changes</button>
  </div>
</div>
     

  </section>
</template>
<script>
import RestaurantService from "../services/RestaurantService.js";
import AuthService from "../services/AuthService.js";
export default {
  name: "edit-profile",
  data() {
    return {
      user: {},
      userCategories: [],
      foodCategories: [],
      editProfileErrors: false,
      editProfileErrorMsg: "There was an issue editing your profile,",
    };
  },
  methods: {
    editProfile() {
      this.user.userDetails.activeCategoryId = this.userCategories;
      AuthService.updateUser(this.user).then((response) => {
        this.$store.commit("UPDATE_USER", response.data);
        this.user = this.$store.state.user;
        this.$router.push("/profile");
      });
    },
  },
  created() {
    this.user = this.$store.state.user;
    this.userCategories = this.$store.state.user.userDetails.activeCategoryId;
    RestaurantService.getAvailableCategories().then((response) => {
      this.foodCategories = response.data;
    });
  },
};
</script>
<style scoped>
/*
.container input {
  display: flex;
  padding: 2px;
  margin-top: 6px;
  margin-right: 5px;
  cursor: pointer;
}
span.checkmark {
  display: flex;
  flex-basis: auto;
  justify-content: space-evenly;
  padding: 5px;
}

.is-primary {
    background-color: #dc6b67;
}

#leftside {
  width: 30vw;
  padding: 15px;
}
#rightside {
  width: 30vw;
}
.container {
  display: flex;
  flex-direction: row;
  position: relative;
  cursor: pointer;
  font-size: 1em;
  font-weight: bold;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.center {
  display: flex;
  align-content: center;
  justify-content: center;
  padding-top: 25%;
  padding-right: 5%;
  width: 50%;
  margin: auto;
}
button {
  background-color: #dc6b67;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  font-weight: 600;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 5px;
  width: 50%;
} */
</style>