<template>
  <main class="main-section">
      <div id="edit-profile-container">
          <div class="leftside">
      <form class="form-edit-profile" @submit.prevent="editProfile">
          <h1 class ="h3 mb-3 font-weight normal">Edit Profile</h1>

          <div class="alert" role="alert" v-if="editProfileErrors">
              {{editProfileErrorMsg}}
          </div>
          <b-field label="First Name">
              <b-input v-model="user.firstName" required></b-input>
          </b-field>
          <b-field label="Last Name">
              <b-input v-model="user.lastName" required></b-input>
          </b-field>
          <b-field label = "Email Address">
              <b-input v-model="user.email">
                  <i class= "fas fa-envelope"></i>
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
          </form>
          </div>
          <div class="rightside"> <br><br>
              <h1 class="h3 mb-3 font-weight normal">Edit Favorite Categories</h1>
          <div class="column" v-for="category in this.foodCategories"
            v-bind:key="category.categoryId">
            
          <label
            class="container"
            
            >{{ category.displayName }}
            </label>
            <span class="checkmark">
            <input
              type="checkbox"
              checked="checked"
              v-bind:id="category.categoryId"
              v-bind:value="category.categoryId"
              v-model.number="user.userDetails.activeCategoryId"
            />
            </span>
          </div>
            </div>
        
      
      </div>
      <button type="submit" v-on:click.prevent="editProfile">Save Changes</button>
      </main>
</template>

<script>
import RestaurantService from '../services/RestaurantService.js'
import AuthService from '../services/AuthService.js'
export default {
    name:"edit-profile",
    created() {
        this.user = this.$store.state.user;
        RestaurantService.getAvailableCategories().then((response)=> {
            this.foodCategories = response.data;
        })
    },

    data() {
        return  {
            user: {},
            foodCategories: [],
            editProfileErrors: false,
            editProfileErrorMsg: "There was an issue editing your profile,",
        }
    },
    methods: {
        editProfile() {
            console.log(this.user);
            AuthService.updateUser(this.user).then((response)=>{
                this.$store.commit(response.data);
            })
            this.user=this.$store.state.user;
            this.$router.push('/profile');
            

        }
    }

}
</script>

<style scoped>
h1 {
  text-align: center;
  font-weight: 700;
  font-size: 1.5em;
}
.main-section {
  background-color: #fdf2f2;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vw;
  height: 100vh;
  justify-content: center;

}
#edit-profile-container {
   
  align-self: center;
  background-color: white;
  display: flex;
  justify-content: center;
 
  padding-right: 15px;
  margin:20px;
}

.form-edit-profile{
    margin: 20px;
    padding: 10px;
}
.container {
    flex-direction: column;
}
.column {
    float: left;
    
}
.checkbox {
    float: left;
}
.leftside {
    width: 50%;
}
.rightside {
    width: 50%;
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
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 5px;
  width: 50%;
  margin-top: 30px;
}
</style>