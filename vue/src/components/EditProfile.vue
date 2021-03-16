<template>
  <main class="main-section">
      <div id="edit-profile-container">
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
          <div v-for="category in user.userDetails.availCategories"
            v-bind:key="category.categoryId">

          <label
            class="container"
            
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
        <button type="submit" v-on:click.prevent="editProfile">Save Changes</button>
      </form>
      </div>
      </main>
</template>

<script>

export default {
    name:"edit-profile",
    created() {
        this.user = this.$store.state.user;
        console.log(this.user);
    },

    data() {
        return  {
            editProfileErrors: false,
            editProfileErrorMsg: "There was an issue editing your profile,",
        }
    },
    methods: {
        editProfile() {
            this.$store.commit("UPDATE_USER", this.user);
            this.user = [];
            this.$router.push('/profile');
            

        }
    }

}
</script>

<style>
h1 {
  text-align: center;
  font-weight: 700;
  font-size: 1.5em;
}
.main-section {
  background-color: #fdf2f2;
  display: flex;
  align-items: center;
  width: 100vw;
  height: 100vh;
  justify-content: center;

}
#edit-profile-container {
    width: 30%;
  align-self: center;
  background-color: white;
  padding-top: 30px;
  padding-bottom: 30px;
  display: flex;
  justify-content: center;
}

.form-edit-profile{
    width: 75%;
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
  width: 100%;
  margin-top: 30px;
}
</style>