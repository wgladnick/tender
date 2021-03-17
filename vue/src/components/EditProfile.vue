<template>
  <main class="main-section">
      <div id="edit-profile-container">
          <div id="leftside" class="card">
      <form class="form-edit-profile" >
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
          <div id="rightside" class="card"> <br><br>
              <h1 class="h3 mb-3 font-weight normal">Edit Favorite Categories</h1>
          <div class="column" v-for="category in this.foodCategories"
            v-bind:key="category.categoryId">
            
             <input
              type="checkbox"
              checked="checked"
              v-bind:id="category.categoryId"
              v-bind:value="category.categoryId"
              v-model.number="userCategories"
            />
          <label
            
            
            >
            {{ category.displayName }}
            </label>
           
            
            
            
            
          </div>
          <section>
                <img src="../assets/TenderFlameWText.png" alt="secondaryLogo" class="center"/>
            </section>
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
       data() {
        return  {
            user: {},
            userCategories: [],
            foodCategories: [],
            editProfileErrors: false,
            editProfileErrorMsg: "There was an issue editing your profile,",
        }
    },
    methods: {
        editProfile() {
            
            this.user.userDetails.activeCategoryId = this.userCategories;
            AuthService.updateUser(this.user).then((response)=>{
                this.$store.commit("UPDATE_USER", response.data);
                this.user=this.$store.state.user;
            
            this.$router.push('/profile');
            
            })
            

        }
    },
     created() {
        this.user = this.$store.state.user;
        this.userCategories = this.$store.state.user.userDetails.activeCategoryId;
        RestaurantService.getAvailableCategories().then((response)=> {
            this.foodCategories = response.data;
        })
    },

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
  margin:20px;
}

.form-edit-profile{
    margin: 20px;
    padding: 10px;
}

.column {
    float: left;
    justify-content: space-evenly;
    align-content: space-around;
}
.container input {
    display: flex;
    padding: 2px;
    margin-top: 6px;
    margin-right: 5px;
    cursor: pointer;
}

span.checkmark {
    display:flex;
    flex-basis: auto;
    justify-content: space-evenly;
    padding: 5px;
    
    
}
#leftside {
    width: 30vw;
    padding:15px;
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
   margin-left: auto;
   margin-right: auto;
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
  font-weight: 600;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 5px;
  width: 50%;
  
}
</style>